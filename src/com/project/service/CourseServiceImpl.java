package com.project.service;
import java.util.ArrayList;
import java.util.List;

import com.project.entity.Course;
import com.project.entity.Student;
import com.project.exception.CourseException;
import com.project.exception.StudentException;
import com.project.util.Constants;
import com.project.util.StudentCourseUtils;

public class CourseServiceImpl implements CourseService {
	
	private static final List<Course> courses= new ArrayList<>();

	@Override
	public String addCourse(String courseName,String courseDescription,int durationInWeeks,boolean active) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String msg="";
		Course c= new Course(courseName,courseDescription,durationInWeeks,active);
		int courseId=StudentCourseUtils.generatePersonId();
		c.setCourseId(courseId);
		flag=courses.add(c);
		if(flag)
			msg="Course is added successfully.!";
		else {
			msg="There is an error in adding course to the list.Enter the details again.";
			throw new CourseException(msg);
		}
		return msg;
	}

	@Override
	public void viewCourses() {
		// TODO Auto-generated method stub
		if(courses.size()>0) {
			displayCourseHeader();
			displayCourseData();
		}
		else {
			displayCourseHeader();
			System.out.println("There are no courses added in the system.");
			System.out.println(Constants.SINGLELINE);
		}
		
	}

	private void displayCourseHeader() {
		// TODO Auto-generated method stub
		System.out.println(Constants.SINGLELINE);
		System.out.println(Constants.COURSESHEADER);
		System.out.println(Constants.SINGLELINE);
		System.out.println("S.No|"+Constants.COURSEID+"|"+Constants.COURSENAME+"|"+Constants.COURSEDESCRIPTION+"|"+Constants.DURATIONINWEEKS+"|"+Constants.ACTIVE);
		System.out.println(Constants.SINGLELINE);
	}

	private void displayCourseData() {
		// TODO Auto-generated method stub
		for(int i=0;i<courses.size();i++) {
			Course c= courses.get(i);
			int j=i+1;
			System.out.println(j+"   |"+c.getCourseId()+" |"+c.getCourseName()+" |"+c.getCourseDescription()+" |"+c.getDurationInWeeks()+" |"+c.isActive());
		}
		System.out.println(Constants.SINGLELINE);
	}

	@Override
	public String activateCourse(int courseId) {
	    String msg = "";
	    int index = -1;
	    Course c = null;

	    // 1. Scan for the matching course ID
	    for (Course cs : courses) {
	        if (cs.getCourseId() == courseId) {
	            index = courses.indexOf(cs);
	            c = courses.get(index);
	            break; // Optimization: Stop looping once found
	        }
	    }

	    // 2. THROW CUSTOM EXCEPTION: Course ID does not exist in the collection
	    if (index == -1 || c == null) {
	        msg = "Course doesn't exist with given courseId " + courseId;
	        throw new CourseException(msg);
	    }

	    // 3. Process the validation and activation logic cleanly
	    if (!c.isActive()) {
	        c.setActive(true);
	        courses.set(index, c);
	        msg = "Course with id " + c.getCourseId() + " activated successfully.!";
	    } else {
	        // THROW CUSTOM EXCEPTION: Course is already active
	        msg = "Course is already active.";
	        throw new CourseException(msg);
	    }

	    return msg;
	}


	@Override
	public String deactivateCourse(int courseId) {
		// TODO Auto-generated method stub
		String msg="";
		int index=-1;
		
			Course c=null;
			for(Course cs:courses) {
				if(cs.getCourseId()==courseId) {
					index=courses.indexOf(cs);
					c = courses.get(index);
				}
			}
			if(index==-1) {
				msg="Course doesn't exists with given courseId "+courseId;
				throw new CourseException(msg);
			}
			if(c!=null) {
				if(c.isActive()) {
					c.setActive(false);
					courses.set(index, c);
					msg="Course with id"+c.getCourseId()+" deactivated successfully.!";
				}else {
					msg="Course is already deactive.";
					throw new CourseException(msg);
				}
			}
		
		return msg;
	}
	
	public boolean courseIfExists(int courseId) {
		boolean flag=false;
		long count=0;
		if(courses.size()>0) {
			count=courses.stream().filter(s->(s.getCourseId()==courseId)).count();
			if(count>0) {
				flag=true;
			}
			else
				flag=false;
		}
		else {
			flag=false;
		}
		return flag;
	}
	public static List<Course> getCoursesList(){
		return courses;
	} 
}
