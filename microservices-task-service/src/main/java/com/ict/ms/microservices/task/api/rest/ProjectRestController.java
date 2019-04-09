package com.ict.ms.microservices.task.api.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ict.ms.microservices.task.api.rest.dto.NewProjectDto;
import com.ict.ms.microservices.task.api.rest.dto.NewTaskDto;
import com.ict.ms.microservices.task.api.rest.dto.StatusDto;
import com.ict.ms.microservices.task.domain.Project;
import com.ict.ms.microservices.task.domain.ProjectDomainRepository;
import com.ict.ms.microservices.task.domain.Task;
import com.ict.ms.microservices.task.domain.exception.InvalidProjectMemberException;
import com.ict.ms.microservices.task.domain.exception.ProjectNotFoundException;
import com.ict.ms.microservices.task.domain.exception.UserNotFoundException;
import com.ict.ms.microservices.task.domain.vo.UserEmail;
import com.ict.ms.microservices.task.infrastructure.services.users.UserServiceClient;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class ProjectRestController {

	private ProjectDomainRepository projectRepository;

	private UserServiceClient userServiceClient;

	@Autowired
	public ProjectRestController(ProjectDomainRepository projectRepository, UserServiceClient userServiceClient) {
		this.projectRepository = projectRepository;
		this.userServiceClient = userServiceClient;
	}

	@GetMapping(value = "/projects")
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@GetMapping(value = "/projects/{id}")
	public Project findOne(@PathVariable("id") Long id) {
		return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
	}

	@DeleteMapping(value = "/projects/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		return projectRepository.findById(id).map(project -> {
			projectRepository.delete(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());

	}

	@PostMapping(value = "/projects")
	@ResponseStatus(HttpStatus.CREATED)
	public Project createProject(HttpServletRequest request, @RequestBody NewProjectDto newProject) {
		String userEmail = request.getHeader("userId");

		userServiceClient.findOne(userEmail).orElseThrow(() -> new UserNotFoundException(userEmail));

		Project project = Project.builder().name(newProject.getName()).owner(new UserEmail(userEmail)).build();

		return projectRepository.save(project);
	}

	@PostMapping(value = "/projects/{id}/tasks")
	public Task addTaskToProject(@RequestHeader("userId") String userEmail, @RequestBody NewTaskDto newTask,
			@PathVariable("id") Long projectId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ProjectNotFoundException(projectId));

		UserEmail author = new UserEmail(userEmail);

		if (project.isMember(author)) {
			Task task = Task.builder().author(author).name(newTask.getName()).description(newTask.getDescription())
					.assignedTo(newTask.getAssignedTo()).dueDate(newTask.getDueDate()).build();
			project.addTask(task);
			projectRepository.save(project);
			return project.findTask(task.getUuid());
		}

		throw new InvalidProjectMemberException(userEmail);
	}

	@PatchMapping(value = "/projects/{id}")
	public Project updateStatus(@RequestHeader("userId") String userEmail, @RequestBody StatusDto status,
			@PathVariable("id") Long projectId) {

		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new ProjectNotFoundException(projectId));

		UserEmail author = new UserEmail(userEmail);

		if (project.isMember(author)) {
			project.updateStatus(status.getAsEnum());
			return projectRepository.save(project);
		}
		throw new InvalidProjectMemberException(userEmail);
	}
}
