package com.commons;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class Circle implements Shape, ApplicationEventPublisherAware{

	private Point center;
	
	private ApplicationEventPublisher applicationEventPublisher; 
	//@Autowired
    //private ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public void draw() {
		System.out.println("Circle: point is ("+center.getX()+", "+center.getY()+")");
		DrawEvent drawEvent = new DrawEvent(this, "my test");
		System.out.println(drawEvent);
		applicationEventPublisher.publishEvent(drawEvent);
		
	}

	public Point getCenter() {
		return center;
	}

	@Resource(name = "pointA")
	public void setCenter(Point center) {
		this.center = center;
	}

	@PostConstruct
	public void initializeCircle() {
		System.out.println("Init of Circle");
	}

	@PreDestroy
	public void destroyCircle() {
		System.out.println("Destroy of Circle");
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
}
