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
	

app.get('/client/create', function(req, res) {
	//creating client
	
	var fname = req.query.fname;
	var sname = req.query.sname;
	var age = req.query.age;
	var phone_num = req.query.phone_num;

	database.client.create(fname, sname, age, phone_num);
	
});

app.get('/client/login', function(req, res) {
	//Welcome the child
	//get the id of tthe client
	var id = req.query.tagId;
	
	console.log("Welcome");
	database.client.login(id);
	
	//display child interface
	//TODO?
	
});

app.get('/guardian/create', function(req, res) {
	//create guardian
	
	var fname = req.query.fname;
	var sname = req.query.sname;
	var phone_num = req.query.phone_num;

	database.guardian.create(fname, sname, phone_num);
	
});

app.get('/guardian/auth', function(req, res) {
	
	var id = req.query.tagId;
	
	database.guardian.auth(id);
	
});

app.get('/guardian/get', function(req, res) {
	
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

app.get('/fellowship/delete', function(req, res) {
	//delete fellowship from database
	
	var id = req.query.tagId;
	
	database.fellowship.delete(id);
	
});

app.get('/fellowship/create', function(req, res) {
	//create fellowship
	var fname = req.query.fname;
	var sname = req.query.sname;
	var phone_num = req.query.phone_num;

	database.fellowship.create(fname, sname, phone_num);
	//return id;
});

app.get('/fellowship/addJourney', function(req, res) {
	
	//add journey
	var journey_id = req.query.journey_id;
	var fellow_id = req.query.fellow_id;
	
	database.fellowship.addJourney(journey_id, fellow_id);

});

app.get('/fellowship/join', function(req, res) {
	
	//join fellowship
	var client_id = req.query.client_id;
	var fellow_id = req.query.fellow_id;
	
	database.fellowship.join(client_id, fellow_id);
	//return success bool;

});

app.get('/fellowship/deleteJourney', function(req, res) {
	
	//delete fellowship
	
	var journey_id = req.query.journey_id;
	var fellow_id = req.query.fellow_id;
	
	database.fellowship.deleteJourney(journey_id, fellow_id);

});

app.get('/fellowship/get', function(req, res) {
	
	var fellow_id = req.query.fellow_id;
	
	database.fellowship.get(fellow_id);
	
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

	var phone_num = req.query.phone_num;
	var start_loc = req.query.start_loc;
	var end_loc = req.query.end_loc;
	var start_time = req.query.start_time;
	var end_time = req.query.end_time;
	
	database.journey.create(phone_num, start_loc, end_loc, start_time, end_time);
	
	//return journey_id;
	
});


app.get('/journey/start', function(req, res) {
	//start a journey
	var journey_id = req.query.journey_id;
	var start_time = req.query.start_time;
	
	database.journey.start(journey_id, start_time);
		
});

app.get('/journey/end', function(req, res) {
	//end a journey
	
	var journey_id = req.query.journey_id;
	var start_time = req.query.start_time;
	
	database.journey.end(journey_id, start_time);
		
});

app.get('/database', function(req, res) {
	res.sendFile(__dirname + '/database.js');
});

http.listen(3000, function() {
	console.log('listening on *:3000');
});