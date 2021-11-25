//inital connect to socket server
const socket = io();

//constants that represent to elements in html file
const form_sendID = document.querySelector('#send-ID');
const form_sendName = document.querySelector('#send-name');
const id_input = document.querySelector('#IDroom');
const name_input = document.querySelector('#UserName');
const result = document.querySelector(".result");
const start_box = document.querySelector(".start_box");
const quiz_box = document.querySelector(".quiz_box");
const end_box = document.querySelector(".end_box");
const option_list = document.querySelector(".option_list");
const timeText = document.querySelector(".timer .time_left_txt");
const timeCount = document.querySelector(".timer .timer_sec");

// creating the new div tags which for icons
const tickIconTag = '<div class="icon tick"><i class="fas fa-check"></i></div>';
const crossIconTag = '<div class="icon cross"><i class="fas fa-times"></i></div>';

// all variable we use in this project
let id_room,
name_user;

let quiz;
let total_ques = 0;
const MAX_SCORES = 1000; //max score in each question
let score = 0;
let MAX_TIME = 0;
let countdown_Time = 0;
let received_time = new Date();
let selected_Answer = '';
let timerID;

// add event for process data on start_box
form_sendID.addEventListener('submit', (e) => {
    e.preventDefault();

    id_room = id_input.value;
    id_input.value = "";
    socket.emit('joining-room', id_room, message => {
        if (message == "Room exist") {
            document.getElementById("send-ID").style.display = "none";
            document.getElementById("send-name").style.display = "block";
        }
        else {
            id_room = 0;
            alert(message);
        }
    })
})

form_sendName.addEventListener('submit', (e) => {
    e.preventDefault();

    name_user = name_input.value;
    name_input.value = "";

    socket.emit('set-name', id_room, name_user, message => {
        if (message == "OK") {
            document.getElementById('welcome-tag').textContent = `Welcome player, you are in room ${id_room}`;
            document.getElementById("send-name").style.display = "none";
        }
        else {
            document.getElementById('welcome-tag').textContent = "Name has been used, please types again";
        }
    })
})

//start, hidden quizbox and button start
quiz_box.classList.add("hidden");
end_box.classList.add("hidden");
document.getElementById("send-name").style.display = "none";

//all functions

function setMaxQuestion(index) { //call each time event get-question occur
    if (total_ques < index) total_ques = index;
}

function ShowQuestion() //show question received from server
{
    const que_text = document.querySelector(".que_text");
    let que_tag = '<span>' + quiz.question + '</span>';
    let option_tag = '';
    for (var i of quiz.option) {
        option_tag += '<div class="option" onclick="optionSelected(this)"><span>' + i + '</span></div>';
    }
    que_text.innerHTML = que_tag;
    option_list.innerHTML = option_tag;
    clearInterval(timerID);
    MAX_TIME = quiz.Countdown_Time;

    startTimer();
    QuestionCounter(quiz.num);
}

function startTimer() //start counting, get received timex 
{
    received_time = new Date();

    countdown_Time = MAX_TIME;
    timeCount.textContent = countdown_Time;

    if (countdown_Time < 9)  //if timer is less than 9 
    {
        let addZero = timeCount.textContent;
        timeCount.textContent = "0" + addZero; //add a 0 before time value
    }
    countdown_Time--;

    timerID = setInterval(timer, 1000);
    function timer() {
        timeCount.textContent = countdown_Time; //changing the value of timeCount with time value
        countdown_Time--; //decrement the time value
        if (countdown_Time < 9)  //if timer is less than 9 
        {
            let addZero = timeCount.textContent;
            timeCount.textContent = "0" + addZero; //add a 0 before time value

            if (countdown_Time < 0) //if timer is less than 0 
            {
                clearInterval(timerID); //clear counter
                timeText.textContent = "Time Off"; //change the time text to time off
            }
        }
    }
}

function select_CorrectAnswer()
{
    const correctAns = quiz.answer; //getting correct answer from array
    const allOptions = option_list.children.length; //getting all option items
    for (i = 0; i < allOptions; i++) {
        if (option_list.children[i].textContent == correctAns) { //if there is an option which is matched to an array answer 
            option_list.children[i].setAttribute("class", "option correct"); //adding green color to matched option
            option_list.children[i].insertAdjacentHTML("beforeend", tickIconTag); //adding tick icon to matched option
            console.log("Auto selected correct answer.");
        }
    }
}

function QuestionCounter(index) //set index of question on view 
{
    const webCounter = document.querySelector('.total_ques');
    let webCounterTag = '<span><p>' + index + '</p>Of<p>' + total_ques + '</p>Questions</span>';
    webCounter.innerHTML = webCounterTag;
}

function optionSelected(answer) {
    var selected_time = new Date();
    time_left = ((MAX_TIME * 1000 - (selected_time - received_time)) > 0) ? (MAX_TIME * 1000 - (selected_time - received_time)) : 0;

    selected_Answer = answer.textContent;
    
    if (selected_Answer == quiz.answer) {
        score = calculate_score();
    }
    const allOptions = option_list.children.length; //getting all option items

    for (i = 0; i < allOptions; i++) {
        option_list.children[i].classList.add("disabled"); //once user select an option then disabled all options
    }
}

function calculate_score() {
    let current_time_left = time_left;
    return Math.round((MAX_SCORES / 2) * Math.sqrt(current_time_left * 4 / (MAX_TIME * 1000))); //base on equation y = x^2 to get score
}

// event socket process funtion
socket.on('get-question', (maxquestion, question) => {
    score = 0;
    document.getElementById('welcome-tag').textContent = "";
    quiz_box.classList.remove("hidden");
    setMaxQuestion(maxquestion);
    quiz = JSON.parse(question);
    ShowQuestion();
})

socket.on('end-quiz',() => {
    timeCount.textContent = "00";
    clearInterval(timerID); //clear counter
    timeText.textContent = "Time Off"; //change the time text to time off
    const allOptions = option_list.children.length; //getting all option items
    const correctAns = quiz.answer; //getting correct answer from array
    
    const userAns = selected_Answer;
    result.innerHTML = `<div  class="result_item"> ${score} </div>`; // show score
    //show correct answer
    if (userAns == '') { // if player not select any answer
        select_CorrectAnswer();
    } else {
        for (i = 0; i < allOptions; i++) {
            if (option_list.children[i].textContent == userAns) {
                if (userAns == correctAns) {
                    option_list.children[i].classList.add("correct");
                    option_list.children[i].insertAdjacentHTML("beforeend", tickIconTag);
                    console.log('Correct answer');
                } else {
                    option_list.children[i].classList.add("incorrect");
                    option_list.children[i].insertAdjacentHTML("beforeend", crossIconTag);
                    console.log('Inorrect answer');

                    select_CorrectAnswer();
                }
            }
        }
        socket.emit('send-result', id_room, selected_Answer, score);
    }

    for (i = 0; i < allOptions; i++) {
        option_list.children[i].classList.add("disabled"); //once user select an option then disabled all options
    }
    selected_Answer = '';
})

socket.on('admin-disconnect', (message) => {
    alert(message);

    quiz_box.classList.add("hidden");
    end_box.classList.add("hidden");
    document.getElementById("send-ID").style.display = "block";
    document.getElementById("send-name").style.display = "none";
    document.getElementById('welcome-tag').textContent = "";
})

socket.on('return-result',(arr) => {
    quiz_box.classList.add("hidden");
    end_box.classList.remove("hidden");

    const rankings = document.querySelector(".rankings");
    const data = JSON.parse(arr);
    //const max_height = document.querySelector('.end_box').offsetHeight;
    let rankings_item = '';
    // let total = 0;
    // for (var i of data) {
    //     total += i.count;// ?
    // }
    for (var i of data) {
        //console.log(i.Name);
        //let height = (max_height * i.count) / total;
        //result_item += `<div  class="result_item" style="height:${height}px"> ${i.count} </div>`;
        rankings_item += `<div>${i.Name} + ${i.score}</div>`
    }
    rankings.innerHTML = rankings_item;
    console.log(arr);
})

socket.on('get-rank',(score,rank) => {
    const player_rank = document.querySelector(".player_rank");
    var player_rank_item = `<div>u are in rank ${rank} with score ${score},,congrats !</div>`;
    if(parseInt(rank) < 4)
    {
        player_rank_item = `<div>u are in top ${rank} with score ${score}</div>`;
    }
    player_rank.innerHTML = player_rank_item;
})