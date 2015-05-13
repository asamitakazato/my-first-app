package controllers;

import models.Task;

import java.util.Date;
import java.util.List;
import java.util.Random;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;



public class Application extends Controller {

    public static Result index() {
    	return ok(index.render("Your new application is ready."));
    }

    public static Result tasks() {

    	Random rnd = new Random();

    	Task task = new Task();
    	task.name = "ピザを"+ rnd.nextInt(10) + "枚食べる";
    	task.period = new Date();
    	task.save();


    	List<Task> taskList = Task.find.where().eq("name", "ピザを5枚食べる").findList();
    	String now =task.name;
    	int cal = taskList.size();

    	return ok(tasks.render(taskList,now,cal));

    }


    public static Result createTask(){
    	Form<Task> form = Form.form(Task.class).bindFromRequest();

    	if(form.hasErrors()){
    		return badRequest(form.errorsAsJson());
    	}else{
    		Task newTask = form.get();
    		newTask.save();
    		return redirect(routes.Application.tasks());
    	}
    	}

    public static Result help(){
    	return ok(help.render());
    }

}
