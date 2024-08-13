package com.auth.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.auth.entity.RestResponse;
import com.auth.model.TaskVo;


@Service
@FeignClient(name="TASKSERVICE")
public interface TaskServiceFeign {
	@GetMapping("/v0/task/user/{userId}")
	ResponseEntity<RestResponse<List<TaskVo>>> fetchByUserId(@PathVariable(name="userId") long userId);
}
