(function() {
  var questions = [{
    question: "What are 'Haptic Systems'?",
    choices: ['Systems that recreate the sense of taste by stimulating the sinews', 'Systems that recreate the sense of touch by applying forces, vibrations, or motions to the user', 'A type of method used to test new programs', 'A medical testing system'],
    correctAnswer: 1
  }, {
    question: "What is 'Force Feedback'?",
    choices: ['A number on the screen showing the force of a phenomenon inside the system', 'An audible output the plays when something hits you', 'A reaction to something you did in the virtual reality', 'Sensory feedback you get from recieving a hit or shock inside of the virtual reality'],
    correctAnswer: 3
  }, {
    question: "What does 'Telepresence and Tele-existence' mean?",
    choices: ['A real-time sensation of being at a place other than where you are, and being able to interact with the environment', 'Transferring your entire body into virtual reality', 'Having a second body inside a virtual space', 'Being inside of a virtual reality'],
    correctAnswer: 0
  }, {
    question: "What is a 'Visual Artifact of VA'?",
    choices: ['An object given shape in a virtual environment', 'A relic that explains part of the history of virtual reality', 'An immaterial object that exists in the human mind or in a digital environment', 'A very old file on a computer'],
    correctAnswer: 2
  }, {
    question: "What are 'Optical Flex Sensors'?",
    choices: ['A small machine that measures speed using images', 'A flexible camera used to track  motion in the legs and lower back', 'A flexible cable used to track hand and arm movement', 'A flexible lens you can place on a camera'],
    correctAnswer: 2
  }, {
    question: "What does 'Immersive' mean?",
    choices: ['Providing information or stimulation for a number of senses, not only sight and sound', 'A very engrossing experience', 'The feeling when you are completely focused on something', 'A mentally exhausting procedure'],
    correctAnswer: 0  
  }, {
    question: "What is an 'Exoskeleton'?",
    choices: ['The skin of an insect like a fly or bee', 'A piece of clothing or armor made fron bones', 'A rare bone disease that makes your bones grow outwards', 'An artificial external supporting structure'],
    correctAnswer: 3  
  }, {
    question: "What is the 'Oculus Rift'?",
    choices: ['A treadmill with visual display developed and manufactured by Oculus VR', 'A virtual reality headset developed and manufactured by Oculus VR', 'A special pair of glasses developed and manufactured by Oculus VR', 'Special computer screens with 3D capability developed and manufactured by Oculus VR'],
    correctAnswer: 1   
  }, {
    question: "What does 'Cumbersome' mean?",
    choices: ['A person or thing that slows down progress of a group', 'A special type of training to improve your strength', 'Hard to handle or manage because of size or weight', 'A very green cucumber'],
    correctAnswer: 2  
  }, {
    question: "What is a 'User-Friendly-Interface?",
    choices: ['A hardware device or software interface that is easy to use', 'An interface with a very friendly use of words', 'An Artificial Intelligence designed to be friendly towards people', 'An interface with very small letters'],
    correctAnswer: 0  
  }, {
    question: "What is 'Virtual Space'?",
    choices: ['The space a computer file occupies on a hard drive disk', 'An area in virtual reality perceived by users to be a place you can move around in', 'A computer simulation of the Milky Way', 'The working environment of a computer'],
    correctAnswer: 1 
  }];

  var questionCounter = 0; //Tracks question number
  var selections = []; //Array containing user choices
  var quiz = $('#quiz'); //Quiz div object
  
  // Display initial question
  displayNext();
  
  // Click handler for the 'next' button
  $('#next').on('click', function (e) {
    e.preventDefault();
    
    // Suspend click listener during fade animation
    if(quiz.is(':animated')) {        
      return false;
    }
    choose();
    
    // If no user selection, progress is stopped
    if (isNaN(selections[questionCounter])) {
      alert('Please make a selection!');
    } else {
      questionCounter++;
      displayNext();
    }
  });
  
  // Click handler for the 'prev' button
  $('#prev').on('click', function (e) {
    e.preventDefault();
    
    if(quiz.is(':animated')) {
      return false;
    }
    choose();
    questionCounter--;
    displayNext();
  });
  
  // Click handler for the 'Start Over' button
  $('#start').on('click', function (e) {
    e.preventDefault();
    
    if(quiz.is(':animated')) {
      return false;
    }
    questionCounter = 0;
    selections = [];
    displayNext();
    $('#start').hide();
  });
  
  // Animates buttons on hover
  $('.button').on('mouseenter', function () {
    $(this).addClass('active');
  });
  $('.button').on('mouseleave', function () {
    $(this).removeClass('active');
  });
  
  // Creates and returns the div that contains the questions and 
  // the answer selections
  function createQuestionElement(index) {
    var qElement = $('<div>', {
      id: 'question'
    });
    
    var header = $('<h2>Question ' + (index + 1) + ':</h2>');
    qElement.append(header);
    
    var question = $('<p>').append(questions[index].question);
    qElement.append(question);
    
    var radioButtons = createRadios(index);
    qElement.append(radioButtons);
    
    return qElement;
  }
  
  // Creates a list of the answer choices as radio inputs
  function createRadios(index) {
    var radioList = $('<ul>');
    var item;
    var input = '';
    for (var i = 0; i < questions[index].choices.length; i++) {
      item = $('<li>');
      input = '<input type="radio" name="answer" value=' + i + ' />';
      input += questions[index].choices[i];
      item.append(input);
      radioList.append(item);
    }
    return radioList;
  }
  
  // Reads the user selection and pushes the value to an array
  function choose() {
    selections[questionCounter] = +$('input[name="answer"]:checked').val();
  }
  
  // Displays next requested element
  function displayNext() {
    quiz.fadeOut(function() {
      $('#question').remove();
      
      if(questionCounter < questions.length){
        var nextQuestion = createQuestionElement(questionCounter);
        quiz.append(nextQuestion).fadeIn();
        if (!(isNaN(selections[questionCounter]))) {
          $('input[value='+selections[questionCounter]+']').prop('checked', true);
        }
        
        // Controls display of 'prev' button
        if(questionCounter === 1){
          $('#prev').show();
        } else if(questionCounter === 0){
          
          $('#prev').hide();
          $('#next').show();
        }
      }else {
        var scoreElem = displayScore();
        quiz.append(scoreElem).fadeIn();
        $('#next').hide();
        $('#prev').hide();
        $('#start').show();
      }
    });
  }
  
  // Computes score and returns a paragraph element to be displayed
  function displayScore() {
    var score = $('<p>',{id: 'question'});
    
    var numCorrect = 0;
    for (var i = 0; i < selections.length; i++) {
      if (selections[i] === questions[i].correctAnswer) {
        numCorrect++;
      }
    }
    
    score.append('You got ' + numCorrect + ' questions out of ' +
                 questions.length + ' right!!!');
    return score;
  }
})();