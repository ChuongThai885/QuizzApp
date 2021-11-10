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
//array has number length is 5
const IDRoom = [];
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
    socket.on('disconnect', (socket) =>{
        console.log('user disconnected');
    })
})

server.listen(3000,() =>{
    console.log('listening on port 3000')
})