var express = require('express')
const app = express()
const http = require('http')
const cors = require('cors')
const { Console } = require('console')
app.use(cors())
const server = http.createServer(app)
const io = require("socket.io")(server, {
    cors: {
        origin: '*',
        methods: '*'
    }
});
app.use(express.static('public'));
//array has number length is 5, contain ID used of the room
const IDRoom = [];
function GenerateIDRoom() {
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
function RemoveIDRoom(ID) {
    if (IDRoom.includes(ID)) {
        let index = IDRoom.indexOf(ID);
        IDRoom.splice(index, 1);
    }
}
class Selected{
    constructor(answer)
    {
        this.answer = answer;
        this.count = 0;
    }
}
class Room {
    constructor(IDAdmin, ListUser,ListQuiz,ListSelected) {
        this.IDAdmin = IDAdmin;
        this.List_User = ListUser;
        this.ListQuiz = ListQuiz;
        this.ListSelected = ListSelected;
    }
}
class User_Infor {
    constructor(IDUser, Name, score) {
        this.IDUser = IDUser;
        this.Name = Name;
        this.score = score;
    }
}

//contain Room [IDRoom] (has an ID Admin, List of ID user, name  user, score)
const RoomList = [];
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
        var r = new Room(socket.id, [],[],[]);
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
        if (IDRoom.includes(parseInt(id_room))) {
            var newuser = new User_Infor(socket.id, "", 0);
            RoomList[id_room].List_User.push(newuser);
            console.log(RoomList);
            console.log(RoomList[id_room].List_User);

            //join room
            socket.join(id_room.toString());
            cb("Room exist");
        }
        else {
            cb("Room doesn't exist");
        }
    })

    socket.on('set-name', (id_room, name_user, cb) => {
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
        console.log(quizz[0]);
        for (var r of IDRoom) {
            i = parseInt(r);
            if (RoomList[i].IDAdmin == socket.id) {
                RoomList[i].ListQuiz = quizz;
                return;
            }
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