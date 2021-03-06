//const socket = io("http://ce6d-2405-4800-440f-84d-2816-1e62-e16c-b6a7.ngrok.io/");
//const socket = io("http://localhost:3000/");
const socket = io("http://171.251.251.51:3000/");

//constants that represent to elements in html file
const start_box = document.querySelector(".start_box");
const quiz_box = document.querySelector(".quiz_box");
const end_box = document.querySelector(".end_box");
const result = document.querySelector(".result");
const result_icon = document.querySelector(".result-icons");
const option_list = document.querySelector(".option_list");
const timeText = document.querySelector(".timer .time_left_txt");
const timeCount = document.querySelector(".timer .timer_sec");
const button_finish = document.querySelector(".finish_countdown");
const button_next = document.querySelector(".next_ques");

const button_start = document.getElementById('button-start');
const button_cancel = document.getElementById('button-cancel');
const button_lock = document.getElementById('button-lock');
const lock_icon = document.querySelector('.lock-icon');
const button_out = document.getElementById('button-out');
   
let number_of_users = 0;
let quiz;
let total_ques = 0;
let timerID;
let IDRoom = 0;

const answer_icons = [
  '<i class="fas fa-square"></i>',
  '<i class="fas fa-circle"></i>',
  '<i class="fas fa-star"></i>',
  '<i class="fas fa-heart"></i>'
];
let num_icon = 0;

//start, hidden quizbox and button start
quiz_box.classList.add("hidden");
end_box.classList.add("hidden"); 
button_start.setAttribute("hidden", "hidden"); // if users in room = 0 then disable
button_finish.setAttribute("hidden", "hidden");
button_next.setAttribute("hidden", "hidden");
lock_icon.innerHTML = '<i class="fas fa-lock"></i>';

const tickIconTag = '<div class="icon tick"><i class="fas fa-check"></i></div>'; // creating the new div tags which for icons

//all funtions 
function ShowQuestion() //show question received from server
{
	result_icon.innerHTML = "";
    const que_text = document.querySelector(".que_text");
    let que_tag = '<span>' + quiz.question + '</span>';
    let option_tag = '';
    for (var i of quiz.option) {
        option_tag += '<div class="option" onclick="optionSelected(this)"><div class="icon anwsers">' + answer_icons[num_icon] + 
    	'</div><div class="option-text">' + i + '</div></div>';
    	num_icon += 1;
    }
    que_text.innerHTML = que_tag;
    option_list.innerHTML = option_tag;

    button_finish.removeAttribute("hidden");

    MAX_TIME = quiz.Countdown_Time;

    clearInterval(timerID);
    startTimer();

    QuestionCounter(quiz.num);
	num_icon = 0;
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
                    ShowResult(JSON.parse(message));//get data from server and put on view
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

function ShowResult(data) // add items into class result 
{
    const max_height = document.querySelector('.result').offsetHeight;
    let result_item = '';
	let result_icon_tag = '';
    let total = 0;
    for (var i of data) {
        total += i.count;
    }
    for (var i of data) {
        console.log(i.answer);
        let height = (max_height * i.count) / total;
        result_item += `<div class="result_item" style="height:${height}px;"></div>`;
		result_icon_tag += `<div class="result-text">${answer_icons[num_icon]} ${i.count}</div>`;
  		num_icon += 1;
    }
	num_icon = 0;
    result.innerHTML = result_item;
	result_icon.innerHTML = result_icon_tag;
}

//add event button
button_start.addEventListener('click', (e) => //when clicked button start start quiz
{
    start_box.classList.add("hidden");
    quiz_box.classList.remove("hidden");
    socket.emit('start-game', quizes);
})

button_cancel.addEventListener('click', (e) => //when clicked button cancel redirect to home page
{
    location.href = "/QuizzApp/Home";
})

button_out.addEventListener('click', (e) => //when clicked button cancel redirect to home page
{
    location.href = "/QuizzApp/Home";
})

button_lock.addEventListener('click',(e) => //when clicked button lock, lock or unlock room
{
    lock_icon.innerHTML = ((lock_icon.innerHTML) == '<i class="fas fa-lock"></i>') ?`<i class="fas fa-unlock-alt"></i>` : `<i class="fas fa-lock"></i>`;
    socket.emit('change-state-room',IDRoom);
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
        ShowResult(JSON.parse(message));
    });
})

button_next.addEventListener('click', (e) => {
    result.innerHTML = '';
    button_next.setAttribute("hidden", "hidden");
    socket.emit('next-question', IDRoom);
})

//socket event process funtion
socket.on('connect', () => {
    console.log(socket.id + '');
})

socket.emit('create-room', room => {
    IDRoom = room;
    document.querySelector('.header-title').textContent = `Ch??o m???ng, ID room c???a b???n l??: ${room}`;
})

socket.on('joined-user', (message) => {
    if (number_of_users == 0) {
        button_start.removeAttribute("hidden");
    }
    number_of_users++;
    const List_user = document.querySelector('.entry-players');
    List_user.innerHTML += `<div class="player-name"><b>${message}</b></div>`
})

socket.on('get-question', (maxquestion, question) => {
    quiz_box.classList.remove("hidden");
    quiz = JSON.parse(question);
    setMaxQuestion(maxquestion);
    ShowQuestion();
})

socket.on('return-result', (arr) => {
    quiz_box.classList.add("hidden");
    end_box.classList.remove("hidden");

    const rankings = document.querySelector(".rankings");
    const data = JSON.parse(arr);
    //const max_height = document.querySelector('.end_box').offsetHeight;
    let rankings_item = '';
    let inde = 1, cre = 0;
    for (var i of data) {
        if (inde == 1) {
            rankings_item += `
            <div class="position-container">
				<img src="img/first.png" class="picture-container">
				<div class="general first">
					<div class="position-name">
						<b>${i.Name}</b>
					</div>
				</div>
			</div>`;
            cre = i.score;
            inde++;
        }
        else if(inde == 2)
        {
            rankings_item = `
            <div class="position-container">
				<img src="img/second.png" class="picture-container">
				<div class="general second">
					<div class="position-name">
						<b>${i.Name}</b>
					</div>
				</div>
			</div>` + rankings_item;
            cre = i.score;
            inde++;
        }
        else if(inde == 3)
        {
            rankings_item += `
			<div class="position-container">
				<img src="img/third.png" class="picture-container">
				<div class="general third">
					<div class="position-name">
						<b>${i.Name}</b>
					</div>
				</div>
			</div>`;
            cre = i.score;
            inde++;
        }
    }
    button_cancel.removeAttribute("hidden");
    rankings.innerHTML = rankings_item;
    console.log(arr);
    rankings.innerHTML = rankings_item;
    console.log(arr);
})