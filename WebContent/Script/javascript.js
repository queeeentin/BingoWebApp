window.onload = iniAll;

// debugger;
var valueset = new Set();
var bgballset = new Set();
var clickedElement = new Array();

function startGame(){
	valueset.clear();
	newcard();
	fillUpBingoSlot();
	
	//clean up all the global variable for a new game
	var num = clickedElement.length;
	for (var i = 0; i < num; i++) {
		clickedElement.pop();
		console.log(clickedElement);
	}
	bgballset.clear;
	
}

function fillUpBingoSlot(){
	var callCount = 0;
	var repeater = setInterval(function(){
		 if (callCount < 76){
			 bgBall(callCount);
			 callCount++;
		 }else{
			 
			 clearInterval(retpeater);
		 }
	 },1000 );
	
 	
 	
 	
}

/*Generate a random number between 1 to 75 and display it on a Bingo Ball slot
 * parm: current Bingo slot
 * reutn: the next slot
 */
function bgBall(curSlotNum){
	//debugger;
	var tempSlotNum = curSlotNum % 6;

	if (tempSlotNum == 0 && curSlotNum >= 6){
		debugger;
		for (var i = 0; i <= 5; i++) {
		var slot = "num" + i;
		document.getElementById(slot).innerHTML = "";

		}	
	}

	var bgNumber = Math.floor(Math.random()*75)+1;
	while(bgballset.has(bgNumber)){
		bgNumber = Math.floor(Math.random()*75)+1;
	}
	bgballset.add(bgNumber);
	console.log(bgballset);
	var curtd = "num" + tempSlotNum;
	document.getElementById(curtd).innerHTML = bgNumber;

	
}


function iniAll(){
	newcard();
	}

/*Generate a new Bingo Chart
 * parm: None 
 * Return: None
 * 
 */
function newcard(){
	for (var i = 0; i<=24; i++) {
			if (i!=12){ setsquare(i) }
			}
}

function setsquare (thisSquare){
	var newNumber = getbase(thisSquare)+ randomNum()+1;
	while (valueset.has(newNumber)){
		var newNumber = getbase(thisSquare)+ randomNum()+1;
	}
	valueset.add(newNumber);
	document.getElementById(thisSquare).innerHTML = newNumber;
	//debugger;
	document.getElementById(thisSquare).className="";
	document.getElementById(thisSquare).onmousedown = toggleColor;
	
}

function getbase(squareNumber){
	
	var col = parseInt(squareNumber / 5);
	var baseNumber = col*15;
	return baseNumber;
	
}

function randomNum(){
	var random = Math.floor(Math.random()*15)+1;
	return random;
}

function toggleColor(evt){
	if(evt){
		var thisSquare = evt.target;
		var squareId = thisSquare.id;
	}else{
		var thisSquare = window.event.srcElement;
	}
	if(thisSquare.className ==""){
		thisSquare.className = "pickedBG";
		//var idbit = Math.pow(2,squareId );
		clickedElement.push(squareId);
		console.log(clickedElement);
	}else{
		thisSquare.className="";
		clickedElement.pop(squareId);
		console.log(clickedElement);

	}
	
}

function checkwin () {
	//debugger;
	var winningOption =0;
	var winningPattern = [0,0,1,0,0,0,0,1,0,0,1,1];
	for (var i = 0; i < clickedElement.length; i++) {
		var squareId = parseInt(clickedElement[i]);
		var col = parseInt(squareId / 5);
		var row = parseInt(squareId % 5) +5;
		
		winningPattern[col] += 1;
		winningPattern[row] +=1;

		var diagonalOne = [0,6,12,18,24];
		var indexOne = diagonalOne.indexOf(squareId);
		var diagonalTwo = [4,8,12,16,20];
		var indexTwo = diagonalTwo.indexOf(squareId);

		if(indexOne != -1){
			winningPattern[10] += 1;
		}
		if(indexTwo !=-1){
			winningPattern[11]+=1;
		}
	}
	winningOption = winningPattern.indexOf(5);

	if(winningOption != -1){
		alert("You have won the Bingo Game with patten " + winningOption);

	}else{
		alert("Loser!! You didn't get a Bingo")
	}
}



























