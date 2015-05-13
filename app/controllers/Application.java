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
    	System.out.println(flash("foo"));
    	return ok(index.render("Your new application is ready."));
    }

    public static Form<Task> taskForm = Form.form(Task.class);

    public static Result tasks() {
    	flash ("foo","pizza");
    	List<Task> taskList = Task.find.all();
        return ok(tasks.render(taskList, taskForm));
    }



    public static Result createTask(){
    	Form<Task> form = taskForm.bindFromRequest();

    	if(form.hasErrors()){
    		List<Task> taskList = Task.find.all();
    		return badRequest(tasks.render(taskList, form));
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
