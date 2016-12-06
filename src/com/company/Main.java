package com.company;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;


public class Main {

    public static User user;

    public static void main(String[] args) {
        ArrayList<Messages> note = new ArrayList<>();

        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    if (user == null) {
                        return new ModelAndView(m, "index.html");

                    } else {

                        m.put("name", user.name);
                        m.put("message", note);
                        return new ModelAndView(m, "messages.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/createUser",
                ((request, response) -> {
                    String name = request.queryParams("newUserName");
                    user = new User(name);

                    response.redirect("/");
                    return "";

                })
        );

        Spark.post(
                "/createMessages",
                ((request, response) -> {
                    String blog = request.queryParams("newMessage");
                    note.add(new Messages(blog));
                    response.redirect("/");
                    return "";
                })
        );


    }
}





