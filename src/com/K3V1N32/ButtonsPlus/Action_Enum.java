package com.K3V1N32.ButtonsPlus;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Action_Enum {
	
	ArrayList<Method> aaa = new ArrayList<Method>();
	
	@ActionMethod(pNode = "buttonsplus.text", name = "text")
	public void action_text(Player p, String text) {
		p.sendMessage(text);
	}
	
	
	
	
}
