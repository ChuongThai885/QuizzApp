
//create server socket
var express = require('express')
const app = express()
const http = require('http')
const cors = require('cors')
app.use(cors())
const server = http.createServer(app)
const io = require("socket.io")(server, {
    cors: {
        origin: '*',
        methods: '*'
    }
});
app.use(express.static('public'));

//All variables
const IDRoom = [];   // contain ID of the room
const RoomList = []; // contain Room [IDRoom] (has an ID Admin, List of ID user, name  user, score)

// All classes
class Selected {
    constructor(answer) {
        this.answer = answer;
        this.count = 0;
    }
}
class Room {
    constructor(IDAdmin, ListUser, ListQuiz, ListSelected, IsAvailable) {
        this.IDAdmin = IDAdmin;
        this.List_User = ListUser;
        this.ListQuiz = ListQuiz;
        this.ListSelected = ListSelected;
        this.IsAvailable = IsAvailable;
    }
}
class User_Infor {
    constructor(IDUser, Name, score) {
        this.IDUser = IDUser;
        this.Name = Name;
        this.score = score;
    }
}

// All funcions
function GenerateIDRoom() { // use for IDRoom[]
    let random = Math.floor((Math.random() * 89999) + 10000);
    if (!IDRoom.includes(random)) {
        IDRoom.push(random);
        console.log('ID room list: ' + IDRoom);
        return random;
    }
    else if (IDRoom.length < 90000) {
        return GenerateIDRoom();
    }
    else return "No more Room available";
}

function RemoveIDRoom(ID) { // use for IDRoom[]
    if (IDRoom.includes(ID)) {
        let index = IDRoom.indexOf(ID);
        IDRoom.splice(index, 1);
    }
}

function getQuestion(i) {
    if (RoomList[i].ListQuiz.length > 0) {
        let ques = RoomList[i].ListQuiz[0];
        RoomList[i].ListQuiz.splice(0, 1);
        console.log(ques);
        return ques;
    } return "";
}
//vd

app.get('/', (req, res) => {
    res.sendFile(__dirname + "/lobby.html");
})
app.get('/playgame', (req, res) => {
    res.sendFile(__dirname + "/index.html");
})


io.on('connection', (socket) => {
    console.log(socket.id);
    console.log('user connected')
    //for admin, connect to server and create room
    socket.on('create-room', (cb) => {
        console.log(socket.id + ",");
        var ID = GenerateIDRoom();
        var r = new Room(socket.id, [], [], [], true);
        RoomList[ID] = r;
        console.log(r.IDAdmin);
        console.log(RoomList);

        //join room
        socket.join(ID.toString());

        cb(ID);
    })

    //for user
    socket.on('joining-room', (id_room, cb) => {
        console.log(id_room);
        console.log(IDRoom);
        var idRoom = parseInt(id_room);
        if (IDRoom.includes(idRoom)) {
            var newuser = new User_Infor(socket.id, "", 0);
            RoomList[idRoom].List_User.push(newuser);
            console.log(RoomList);
            console.log(RoomList[idRoom].List_User);

            //join room
            socket.join(id_room.toString());
            cb("Room exist");
        }
        else {
            cb("Room doesn't exist");
        }
    })

    socket.on('set-name', (idRoom, name_user, cb) => {
        var id_room = parseInt(idRoom);
        console.log(id_room + "," + name_user + "," + socket.id);
        for (var user of RoomList[id_room].List_User) {
            if (user.Name == name_user) {
                cb("error");
                return;
            }
        }
        for (var user of RoomList[id_room].List_User) {
            if (user.IDUser == socket.id) {
                user.Name = name_user;
                socket.to(RoomList[id_room].IDAdmin).emit('joined-user', name_user);
                cb("OK");
            }
        }
        console.log(RoomList[id_room].List_User)
    })

    socket.on('start-game', (quizz) => {
        for (var r of IDRoom) {
            i = parseInt(r);
            if (RoomList[i].IDAdmin == socket.id) {
                RoomList[i].ListQuiz = quizz;
                console.log(RoomList[i].ListQuiz);
                var number_of_ques = RoomList[i].ListQuiz.length; //get max number in List Quiz
                RoomList[i].IsAvailable = false; // so player can't join this room

                var question = getQuestion(i);
                RoomList[i].ListSelected = [];
                for (var a of question.option) {
                    var sel = new Selected(a);
                    RoomList[i].ListSelected.push(sel);
                }
                io.to(i.toString()).emit('get-question', number_of_ques, JSON.stringify(question));
                return;
            }
        }
    })

    socket.on('get-result', (idRoom, cb) => {
        socket.to(idRoom.toString()).emit('end-quiz'); // event for player to submit ans
        RoomList[idRoom].List_User.sort((a, b) => (a.score > b.score) ? 1 : ((b.score > a.score) ? -1 : 0))
        console.log(RoomList[idRoom].List_User);

        setTimeout(() => {
            var res = JSON.stringify(RoomList[idRoom].ListSelected);
            console.log("set time out:" + res);
            cb(res); //sent data back to manager
        }, 1000);
    })

    socket.on('send-result', (id_Room, answer, score) => //catch event from player when they select any answer on form
    {
        var idRoom = parseInt(id_Room);
        for (var a of RoomList[idRoom].ListSelected) {
            if (answer == a.answer) // count for statistics how many time answer is selected
            {
                a.count++;
                break;
            }
        }
        for (var user of RoomList[idRoom].List_User) {
            if (user.IDUser == socket.id) // set score for player
            {
                user.score += score;
                return;
            }
        }
    })

    socket.on('next-question', (idRoom) => {
        console.log("next ques: " + idRoom);
        var number_of_ques = RoomList[idRoom].ListQuiz.length; //get max number in List Quiz
        console.log(number_of_ques);
        if (number_of_ques == 0) {
            var arr = [];
            
            for (var i = 0; i < 3; i++) {
                if(!(RoomList[idRoom].List_User[i] === 'undefined'))
                {
                    arr.push(RoomList[idRoom].List_User[i]);
                }
            }
            
            console.log(arr);
            io.to(idRoom.toString()).emit('return-result', JSON.stringify(arr));
            //if(not admin) sent back score and rank
        }
        else {
            var question = getQuestion(idRoom);
            RoomList[idRoom].ListSelected = [];
            for (var a of question.option) {
                var sel = new Selected(a);
                RoomList[idRoom].ListSelected.push(sel);
            }
            io.to(idRoom.toString()).emit('get-question', number_of_ques, JSON.stringify(question));
            return;
        }
    })

    socket.on('disconnect', () => {
        console.log('user disconnected');
        for (var r of IDRoom) {
            console.log(r);
            i = parseInt(r);
            if (RoomList[i].IDAdmin == socket.id) {

                socket.to(r.toString()).emit('admin-disconnect', "admin has just disconnect, please try later");
                RoomList[i] = {};
                RemoveIDRoom(i);
                console.log(IDRoom);
                console.log(RoomList);
                return;
            }
        }
    })
})

server.listen(3000, () => {
    console.log('listening on port 3000')
})