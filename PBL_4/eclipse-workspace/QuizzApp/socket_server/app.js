var express = require('express')
const app = express()
const http = require('http')
const cors = require('cors')
app.use(cors())
const server = http.createServer(app)
const io = require("socket.io")(server, {
    cors : {
        origin : '*',
        methods: '*'
    }
    // handlePreflightRequest: (req, res) => {
    //     const headers = {
    //         "Access-Control-Allow-Headers": "Content-Type, Authorization",
    //         "Access-Control-Allow-Origin": "*",
    //         "Access-Control-Allow-Credentials": true
    //     };
    //     // res.writeHead(200, headers);
    //     // res.end();
    // }
});
//array has number length is 5, contain ID used of the room
const IDRoom = [11111,21356,12345];
function GenerateIDRoom()
{
    let random = Math.floor(Math.random() * 99999) + 10000;
    if(!IDRoom.includes(random))
    {
        IDRoom.push(random);
        console.log('ID room list: ' + IDRoom);
        return random;
    }
    else if (IDRoom.length < 90000)
    {
        return GenerateIDRoom();
    }
    else return "No more Room available";
}
function RemoveIDRoom(ID)
{
    if(IDRoom.includes(ID))
    {
        let index = IDRoom.indexOf(ID);
        IDRoom.splice(index,1);
        console.log('ID room list: ' + IDRoom);
    }
}
class Room
{
    constructor(IDAdmin, LtUser)
    {
        this.IDAdmin = IDAdmin;
        this.LtUser = LtUser;
    }
}

//contain Room [IDRoom] (has an ID Admin, List of ID user, name  user, score)
const RoomList = [];
var b = new Room(123,[123,456]);
RoomList.push(b);
var c = RoomList[0];
console.log(c.LtUser);
//vd

app.get('/',(req,res)=>{
    res.sendFile(__dirname + "/lobby.html");
})
app.get('/playgame',(req,res)=>{
    res.sendFile(__dirname + "/index.html");
})


io.on('connection',(socket) =>{
    console.log('user connected')
    socket.on('on-chat',data =>{
        io.emit('user-chat',data)
    })
    //for admin, connect to server and create room
    socket.on('create-room', (cb) =>{
        console.log(IDRoom);
        cb("Hello from server");
    })
    //for user
    socket.on('joining-room',(id_room,cb) =>{
        console.log(id_room);
        console.log(IDRoom);
        if(IDRoom.includes(parseInt(id_room)))
        {
            cb("Room exist");
        }
        else
        {
            cb("Room doesn't exist");
        }
    })
    socket.on('disconnect', (socket) =>{
        console.log('user disconnected');
    })
})

server.listen(3000,() =>{
    console.log('listening on port 3000')
})