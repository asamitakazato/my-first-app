package controllers;

import models.Task;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	return ok(index.render("Your new application is ready."));
    }

    public static Result tasks() {

    	Task task = new Task();
    	task.name = "Pizzaを食べる";
    	task.period = new Date();
    	task.save();

    	task.name = "やっぱり唐揚げもいい";
    	task.update();



    	List<Task> taskList = Task.find.all();
    	return ok(tasks.render(taskList));

    }

    public static Result help(){
    	return ok(help.render());
    }

}
