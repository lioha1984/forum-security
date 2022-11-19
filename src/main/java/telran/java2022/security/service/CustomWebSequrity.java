package telran.java2022.security.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.accounting.dao.UserAccountRepository;
import telran.java2022.accounting.model.UserAccount;
import telran.java2022.post.dao.PostRepository;
import telran.java2022.post.model.Post;

@Service("customSecurity")
@RequiredArgsConstructor
public class CustomWebSequrity {
	final PostRepository postRepository;
	final UserAccountRepository userAccountRepository;
	
	public boolean checkPostAuthor(String postId, String userName) {
		Post post = postRepository.findById(postId).orElse(null);
		return post != null && userName.equalsIgnoreCase(post.getAuthor());
	}
	
	public boolean checkExpirationPeriod(String userName) {
		UserAccount user = userAccountRepository.findById(userName).orElse(null);
		return user != null && LocalDateTime.now().isBefore(user.getExpirationDay());
	}
}
