const socket = io();
let quiz = [];
let total_ques = 0;
quiz = [
  {
    num: 1,
    Countdown_Time: 20,
    question: 'Last Christmas, I gave you my heart. But the very next day, you gave it away. This year, to save me from tears I`ll give it to someone special. Last Christmas, I gave you my heart. But the very next day, you gave it away. This year, to save me from tears I`ll give it to someone special',
    option: [
      'giấy lộn',
      'giấy chứng nhận sở hữu một phần doanh nghiệp',
      'giấy chứng nhận số tiền doanh nghiệp vay',
      'Last Christmas, I gave you my heart. But the very next day, you gave it away. This year, to save me from tears I`ll give it to someone special'
    ],
    answer: 'giấy chứng nhận sở hữu một phần doanh nghiệp'
  },
  {
    num: 2,
    Countdown_Time: 20,
    question: 'Sử dụng đòn bẩy margin có tốt không?',
    option: ['Có', 'Không', 'Tùy'],
    answer: 'Tùy'
  },
  {
    num: 3,
    Countdown_Time: 20,
    question: 'Bản chất của thị trường tài chính là tiền từ túi người này sang túi người khác?',
    option: ['True', 'False'],
    answer: 'True'
  }
];
const answer_icons = [
  '<i class="fas fa-square"></i>',
  '<i class="fas fa-circle"></i>',
  '<i class="fas fa-star"></i>',
  '<i class="fas fa-heart"></i>'
];
let num_icon = 0;
const MAX_SCORES = 1000; //max score in each question
let MAX_TIME = 0;
let countdown_Time = 0; //mark
let received_time = new Date();
let selected_Answer = '';
let timerID;
//constants that represent to elements in html file
const quiz_box = document.querySelector(".quiz_box");
const end_box = document.querySelector(".end_box");
const option_list = document.querySelector(".option_list");
const timeText = document.querySelector(".timer .time_left_txt");
const timeCount = document.querySelector(".timer .timer_sec");
// creating the new div tags which for icons
const tickIconTag = '<div class="icon tick"><i class="fas fa-check"></i></div>';
const crossIconTag = '<div class="icon cross"><i class="fas fa-times"></i></div>';

//quiz_box.classList.add("hidden");

//show question received from server
function ShowQuestion() {
  const que_text = document.querySelector(".que_text");
  let que_tag = '<span>' + quiz[0].question + '</span>';
  let option_tag = '';
 for (var i of quiz[0].option) {
    option_tag += '<div class="option" onclick="optionSelected(this)"><div class="icon anwsers">' + answer_icons[num_icon] + 
    '</div><div class="option-text">' + i + '</div></div>';
    num_icon += 1;
  }
  num_icon = 0;  que_text.innerHTML = que_tag;
  option_list.innerHTML = option_tag;
  clearInterval(timerID); // như v k
  MAX_TIME = quiz[0].Countdown_Time;

  startTimer();
  QuestionCounter(quiz[0].num);
}

//set index of question on view
function QuestionCounter(index) {
  const webCounter = document.querySelector('.total_ques');
  let webCounterTag = '<span><p>' + index + '</p>Of<p>' + total_ques + '</p>Questions</span>';
  webCounter.innerHTML = webCounterTag;
}

function optionSelected(answer) {
  var selected_time = new Date();
  time_left = ((MAX_TIME * 1000 - (selected_time - received_time)) > 0) ? (MAX_TIME * 1000 - (selected_time - received_time)) : 0;

  console.log(selected_time - received_time);
  console.log(received_time + "," + selected_time + "\n" + time_left);
  selected_Answer = answer.textContent;
  let score = 0;
  if (selected_Answer == quiz[0].answer) {
    score = calculate_score();
    console.log(score + "inner");
  }
  const allOptions = option_list.children.length; //getting all option items

  console.log(score);

  for (i = 0; i < allOptions; i++) {
    option_list.children[i].classList.add("disabled-selected"); //once user select an option then disabled all options
    //option_list.children[i].setAttribute("class", "option disabled");
  }
}

function calculate_score() {
  let current_time_left = time_left;
  return Math.round((MAX_SCORES / 2) * Math.sqrt(current_time_left * 4 / (MAX_TIME * 1000))); //base on equation y = x^2 to get score
}

function startTimer() {
  //start counting, get received timex
  received_time = new Date();

  countdown_Time = MAX_TIME;
  timeCount.textContent = countdown_Time;
  countdown_Time--;

  timerID = setInterval(timer, 1000);
  function timer() {
    timeCount.textContent = countdown_Time; //changing the value of timeCount with time value
    countdown_Time--; //decrement the time value
    if (countdown_Time < 9) { //if timer is less than 9
      let addZero = timeCount.textContent;
      timeCount.textContent = "0" + addZero; //add a 0 before time value
    }
    if (countdown_Time < 0) { //if timer is less than 0
      clearInterval(timerID); //clear counter
      timeText.textContent = "Time Off"; //change the time text to time off
      const allOptions = option_list.children.length; //getting all option items
      const correctAns = quiz[0].answer; //getting correct answer from array
      console.log(quiz[0].answer);
      const userAns = selected_Answer;
      for (i = 0; i < allOptions; i++) {
        option_list.children[i].classList.remove("disabled-selected"); 
      }
      //show correct answer
      if (userAns == '') {

        for (i = 0; i < allOptions; i++) {
          if (option_list.children[i].textContent == correctAns) { //if there is an option which is matched to an array answer 
            //option_list.children[i].setAttribute("class", "option correct"); //adding green color to matched option
            //option_list.children[i].classList.remove("disabled");
            option_list.children[i].classList.add("correct");
            option_list.children[i].insertAdjacentHTML("beforeend", tickIconTag); //adding tick icon to matched option
            console.log("Auto selected correct answer.");
          }
        }
      } else {
        for (i = 0; i < allOptions; i++) {
          if (option_list.children[i].textContent == userAns) {
            if (userAns == correctAns) {
              //option_list.children[i].classList.remove("disabled");
              option_list.children[i].classList.add("correct");
              
              //option_list.children[i].setAttribute("class", "option correct");
              
              option_list.children[i].insertAdjacentHTML("beforeend", tickIconTag);
              console.log('Correct answer');
            } else {
              //option_list.children[i].classList.remove("disabled");
              option_list.children[i].classList.add("incorrect");
              option_list.children[i].insertAdjacentHTML("beforeend", crossIconTag);
              console.log('Inorrect answer');

              for (i = 0; i < allOptions; i++) {
                if (option_list.children[i].textContent == correctAns) { //if there is an option which is matched to an array answer 
                  //option_list.children[i].classList.remove("disabled");
                  option_list.children[i].classList.add("correct");
                  //option_list.children[i].setAttribute("class", "option correct"); //adding green color to matched option
                  option_list.children[i].insertAdjacentHTML("beforeend", tickIconTag); //adding tick icon to matched option
                  console.log("Auto selected correct answer.");
                }
              }
            }
          }
        }
      }

      for (i = 0; i < allOptions; i++) {
        option_list.children[i].classList.add("disabled"); //once user select an option then disabled all options
      }
      selected_Answer = '';
    }
  }
}

const button_start = document.querySelector('#button-start');
// button_start.setAttribute("hidden","hidden");
button_start.addEventListener('click', (e) => {
  if (quiz.length == 1) {
    console.log('quizz completed');
  }
  else {
    quiz.splice(0, 1);
    ShowQuestion();
  }
})

//main
total_ques = quiz.length;
ShowQuestion();

//nayf ne

const arr = [
  { "answer": "giấy lộn", "count": 1 },
  { "answer": "giấy chứng nhận sở hữu một phần doanh nghiệp", "count": 5 },
  { "answer": "giấy chứng nhận số tiền doanh nghiệp vay", "count": 10 }
]
// total = tổng đáp án
//  get height : const max_height = document.querySelector('#result').height();
//  
let total = 0;
for (var i of arr) {
  total += i.count;
}

console.log(total);
let result = document.querySelector('#result');
const max_height = document.querySelector('#result').offsetHeight;
//max_height/total * count tao cai arr size hoạc la bo do lun cai list ni cung dc du, man rieng cho lanh :v
let arr1 = [];
let result_icon = document.querySelector(".result-icons");
// for(var i of arr)
// {
//     let height = (max_height*i.count)/total
//     result.append( $(`<div  class="result_item" style="height:${height}px"> ${height} </div>`))
// }
let option_tag = '';
let result_icon_tag = '';
for (var i of arr) {
  let height = (max_height*i.count)/total;
  option_tag += `<div class="result_item" style="height:${height}px;"></div>`;
  result_icon_tag += `<div class="result-text">${answer_icons[num_icon]} ${i.count}</div>`;
  num_icon += 1;
}
num_icon = 0;
result.innerHTML = option_tag;
result_icon.innerHTML = result_icon_tag;
