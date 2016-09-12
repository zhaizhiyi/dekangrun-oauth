package com.htche.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.htche.oauth.entity.OauthClientDetails;

@Controller
public class ClientDetailsController {

	@RequestMapping(value = "/registerclient", method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView view = new ModelAndView("jsp/clientdetails/register_client");
		view.addObject("formDto", new OauthClientDetails());
		
		return view;
	}
	

	public ModelAndView list()
	{
		ModelAndView view=new ModelAndView("");
		return view;
	}	

	public ModelAndView add()
	{
		ModelAndView view=new ModelAndView("");

		return view;
	}
}
