package com.helloworld.backend_api.pretest.service;

import com.helloworld.backend_api.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreTestProblemService {

  private final ProblemRepository problemRepository;

}