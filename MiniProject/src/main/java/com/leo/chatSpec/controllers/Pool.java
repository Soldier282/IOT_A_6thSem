package com.leo.chatSpec.controllers;

import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.stereotype.Component;

import com.leo.chatSpec.models.User;

@Component
public class Pool {
	private final Queue<User> waitQueue = new ConcurrentLinkedQueue<User>();
	private final Map<String, User> serverSpecific = new ConcurrentHashMap<>();
	private final Map<User, User> pairedUsersMap = new ConcurrentHashMap<>();
	
//	@Lazy
//	@Autowired
//	private MessageController messageController;
	
	public void testFunc() {
		System.out.println("works");
	}
	
	public synchronized boolean verify(User sender, String recipient)
	{
		return Objects.equals(pairedUsersMap.get(sender).getSeshId(), recipient);
	}
	public synchronized String connectWithServer(User user) throws InterruptedException
	{
		if(serverSpecific.containsKey(user.getServer()))
		{
			User recipientUser = serverSpecific.get(user.getServer());
			serverSpecific.remove(user.getServer());
			pairedUsersMap.put(user, recipientUser);
			pairedUsersMap.put(recipientUser, user);
//			messageController.sendConnection(user.getSeshId(), recipientUser.getSeshId());
			return recipientUser.getSeshId();
		}
		else {
			serverSpecific.put(user.getServer(), user);
			return null;
//			messageController.sendConnection(user.getSeshId(), pairedUsersMap.get(user).getSeshId());
		}
	}
	public synchronized String connectWithoutServer(User user) throws InterruptedException
	{
		if(!waitQueue.isEmpty()) {
			User secUser = waitQueue.poll();
//			System.out.println("user 2");
			pairedUsersMap.put(user, secUser);
			pairedUsersMap.put(secUser, user);
			return secUser.getSeshId();
//			messageController.sendConnection(user.getSeshId(), secUser.getSeshId());

		}
		else {
			waitQueue.add(user);
//			System.out.println("user 1");
			return null;
//			messageController.sendConnection(user.getSeshId(), pairedUsersMap.get(user).getSeshId());
		}
	}
	
	public synchronized String removeUser(User user) {
        User pairedUser = pairedUsersMap.get(user);
        pairedUsersMap.remove(user);
        if(pairedUser != null) {
            pairedUsersMap.remove(pairedUser);
//            messageController.sendDisconnect(pairedUser.getSeshId());
            return pairedUser.getSeshId();
        } else {
			return null;
		}
//        try {
//            if (pairedUser.getServer() != null) {
//                connectWithServer(pairedUser);
//            } else {
//                connectWithoutServer(pairedUser);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
