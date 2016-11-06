var app = require('express')();
var http = require('http').Server(app);
var database = require('./database.js');

app.get('/', function(req, res) {
	res.sendFile(__dirname + '/index.html');
});

console.log("I AM HERE");
console.log();


database.createTables();


	/*
	var group = database.group.display();
	
	group.forEach(function(entry){
			
			console.log(entry);
			
	});
	*/
	

app.get('/client/create/p', function(req, res) {
	//creating client
	
	var fname = req.query.fname;
	var sname = req.query.sname;
	var age = req.query.age;
	var phone_num = req.query.phone_num;

	database.client.create(fname, sname, age, phone_num);
	
});

app.get('/client/login/p', function(req, res) {
	//Welcome the child
	//get the id of tthe client
	var id = req.query.tagId;
	
	console.log("Welcome");
	database.client.login(id);
	
	//display child interface
	//TODO?
	
});

app.get('/guardian/create/p', function(req, res) {
	//create guardian
	
	var fname = req.query.fname;
	var sname = req.query.sname;
	var phone_num = req.query.phone_num;

	database.guardian.create(fname, sname, phone_num);
	
});

app.get('/guardian/auth/p', function(req, res) {
	
	var id = req.query.tagId;
	
	database.guardian.auth(id);
	
});

app.get('/guardian/get/p', function(req, res) {
	
	var phone_num = req.query.phone_num;
	
	var guardian_info = database.guardian.get(phone_num);
	
	return guardian_info;
	
});

app.get('/guardian/login', function(req, res) {
	//Welcome the parent
	
	console.log("Welcome");
	var phone_num = req.query.phone_num;
	
	database.guardian.login(phone_num);	
	
	//display parent interface
	//TODO?
	
});

app.get('/fellowship/delete/p', function(req, res) {
	//delete group from database
	
	var id = req.query.tagId;
	
	database.fellowship.del(id);
	
});

app.get('/fellowship/create', function(req, res) {
	//create fellowship
	database.fellowship.create(/*name, phone, callback?*/);
	//return id;
});

app.get('/fellowship/addJourney', function(req, res) {
	
	//add journey
	database.fellowship.addJourney(/*journeyID, fellowID*/);

});

app.get('/fellowship/join', function(req, res) {
	
	//join fellowship
	database.fellowship.join(/*clientID, fellowID*/);
	//return success bool;

});

app.get('/fellowship/deleteJourney', function(req, res) {
	
	//delete fellowship
	database.fellowship.deleteJourney(/*journeyID, fellowID*/);

});

app.get('/fellowship/get', function(req, res) {
	//show who is in the group
	
	database.fellowship.get(/*id, callback*/);
	
	/*
	var group = database.group.display();
	
	group.forEach(function(entry){
			
			console.log(entry);
			
	});
		
	res.sendFile(__dirname + '/index.html');
	*/
	
	//return name, child list, parents
});

app.get('/journey/create', function(req, res) {
	//add journey to a group
	var journey = [];
	var supervisor = "";
	var start_time = 0;
	var end_time = 0;
	var start_loc = 0;
	var end_loc = 0;
	journey = database.journey.create(/*supervisor, start_loc, end_loc, start_time, end_time*/);
	database.group.addJourney(journey);	
});


app.get('/journey/start', function(req, res) {
	//start a journey
	
	database.journey.start(/*journey id, time*/);
		
});

app.get('/journey/end', function(req, res) {
	//end a journey
	
	database.journey.end(/*journey id, time*/);
		
});

app.get('/database', function(req, res) {
	res.sendFile(__dirname + '/database.js');
});

http.listen(3000, function() {
	console.log('listening on *:3000');
});