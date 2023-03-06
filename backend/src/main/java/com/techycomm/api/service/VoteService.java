package com.techycomm.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techycomm.api.dto.VoteDto;
import com.techycomm.api.exceptions.PostNotFoundException;
import com.techycomm.api.exceptions.SpringRedditException;
import com.techycomm.api.model.Post;
import com.techycomm.api.model.Vote;
import com.techycomm.api.repository.PostRepository;
import com.techycomm.api.repository.VoteRepository;

import static com.techycomm.api.model.VoteType.UPVOTE;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private  VoteRepository voteRepository;
    private  PostRepository postRepository;
    private  AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
//        return Vote.builder()
//                .voteType(voteDto.getVoteType())
//                .post(post)
//                .user(authService.getCurrentUser())
//                .build();
    	return null;
    }
}
