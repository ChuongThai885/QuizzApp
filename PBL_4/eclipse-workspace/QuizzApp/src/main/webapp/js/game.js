
//constants that represent to elements in html file
const start_box = document.querySelector(".start_box");
const quiz_box = document.querySelector(".quiz_box");
const option_list = document.querySelector(".option_list");
const timeText = document.querySelector(".timer .time_left_txt");
const timeCount = document.querySelector(".timer .timer_sec");
const button_finish = document.querySelector(".finish_countdown");
const button_next = document.querySelector(".next_ques");

const users = document.querySelector('.users');
const button_start = document.querySelector('.button-start');
const button_cancel = document.querySelector('.button-cancel');

let number_of_users = 0;
let quiz;
let total_ques = 0;
let timerID;
let IDRoom = 0;

//start, hidden quizbox and button start
quiz_box.classList.add("hidden");
button_start.setAttribute("hidden", "hidden"); // if users in room = 0 then disable
button_finish.setAttribute("hidden", "hidden");
button_next.setAttribute("hidden", "hidden");

const socket = io("http://116.103.144.150:3000/");

const tickIconTag = '<div class="icon tick"><i class="fas fa-check"></i></div>'; // creating the new div tags which for icons

//all funtions 
function ShowQuestion() //show question received from server
{
    const que_text = document.querySelector(".que_text");
    let que_tag = '<span>' + quiz.question + '</span>';
    let option_tag = '';
    for (var i of quiz.option) {
        option_tag += '<div class="option"><span>' + i + '</span></div>';
    }
    que_text.innerHTML = que_tag;
    option_list.innerHTML = option_tag;

    button_finish.removeAttribute("hidden");

    MAX_TIME = quiz.Countdown_Time;

    clearInterval(timerID);
    startTimer();

    QuestionCounter(quiz.num);
}

function startTimer() //start counting, get received timex
{
    var countdown_Time = MAX_TIME;
    timeCount.textContent = countdown_Time;

    if (countdown_Time < 9) { //if timer is less than 9
        let addZero = timeCount.textContent;
        timeCount.textContent = "0" + addZero; //add a 0 before time value
    }
    countdown_Time--;

    timerID = setInterval(timer, 1000);
    function timer() {
        timeCount.textContent = countdown_Time; //changing the value of timeCount with time value
        countdown_Time--; //decrement the time value
        const allOptions = option_list.children.length; //getting all option items

        if (countdown_Time < 9) { //if timer is less than 9
            let addZero = timeCount.textContent;
            timeCount.textContent = "0" + addZero; //add a 0 before time value

            if (countdown_Time < 0) //if timer is less than 0 
            {
                button_finish.setAttribute("hidden", "hidden");
                button_next.removeAttribute("hidden");

                socket.emit('get-result', IDRoom, message => {
                    console.log(message);//get data from server and put on view
                });
                clearInterval(timerID); //clear counter
                timeText.textContent = "Time Off"; //change the time text to time off

                select_CorrectAnswer();

                for (i = 0; i < allOptions; i++) {
                    option_list.children[i].classList.add("disabled"); //once user select an option then disabled all options
                }
            }
        }
    }
}

function QuestionCounter(index) //set index of question on view
{
    const webCounter = document.querySelector('.total_ques');
    let webCounterTag = '<span><p>' + index + '</p>Of<p>' + total_ques + '</p>Questions</span>';
    webCounter.innerHTML = webCounterTag;
}

function setMaxQuestion(index) //call each time event get-question occur 
{
    if (total_ques < index) total_ques = index;
}

function select_CorrectAnswer() {
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

//socket event process funtion
socket.on('connect', () => {
    console.log(socket.id + '');
})

socket.emit('create-room', room => {
    IDRoom = room;
    document.querySelector('.ID-Room').textContent = `Your room ID is : ${room}`;
})

button_start.addEventListener('click', (e) => //when clicked button start start quiz
{
    start_box.classList.add("hidden");
    quiz_box.classList.remove("hidden");
    socket.emit('start-game', quizes);
})

button_cancel.addEventListener('click', (e) => //when clicked button cancel redirect to home page
{
    location.href = "/QuizzApp/Welcome.jsp";
})

button_finish.addEventListener('click', (e) => // when button finish is being clicked, stop timer, show answer, bla bla... 
{
    button_finish.setAttribute("hidden", "hidden");
    button_next.removeAttribute("hidden");

    timeCount.textContent = "00";
    clearInterval(timerID); //clear counter
    timeText.textContent = "Time Off"; //change the time text to time off
    select_CorrectAnswer();

    socket.emit('get-result', IDRoom, message => {
        console.log(message);//get data from server and put on view
    });
})

button_next.addEventListener('click', (e) => {
    button_next.setAttribute("hidden", "hidden");
    socket.emit('next-question', IDRoom);
})

socket.on('joined-user', (message) => {
    if (number_of_users == 0) {
        button_start.removeAttribute("hidden");
    }
    number_of_users++;
    const Item = document.createElement('li');
    Item.textContent = message;
    users.appendChild(Item);
})

socket.on('get-question', (maxquestion, question) => {
    quiz_box.classList.remove("hidden");
    quiz = JSON.parse(question);
    setMaxQuestion(maxquestion);
    console.log(quiz + "," + maxquestion + "\n" + quiz.answer);
    console.log(JSON.parse(question) + '\n' + JSON.parse(question).answer);
    ShowQuestion();
})

socket.on('return-result', (arr) => {
    console.log(arr);
})
