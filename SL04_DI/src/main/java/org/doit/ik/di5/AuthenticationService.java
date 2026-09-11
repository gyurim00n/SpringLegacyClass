package org.doit.ik.di5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
@Getter
public class AuthenticationService { //8
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthFailLogger failLogger;

	//인증처리
	public AuthInfo authenticate(String id, String password) {
		User user = userRepository.findUserById(id);
		if (user == null)
			throw new UserNotFoundException();
		if (!user.matchPassword(password)) {
			failLogger.insertBadPw(id, password);
			throw new AuthException();
		}

		return new AuthInfo(user.getId());
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setFailLogger(AuthFailLogger failLogger) {
		this.failLogger = failLogger;
	}

}
