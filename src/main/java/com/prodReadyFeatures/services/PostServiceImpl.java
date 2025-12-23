package com.prodReadyFeatures.services;

import com.prodReadyFeatures.dto.PostDto;
import com.prodReadyFeatures.entities.PostEntity;
import com.prodReadyFeatures.exceptions.ResourceNotFoundException;
import com.prodReadyFeatures.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<PostDto> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto inputPost) {
       PostEntity postEntity=modelMapper.map(inputPost,PostEntity.class);
       return modelMapper.map(postRepository.save(postEntity),PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post not found with id " + postId));

        return modelMapper.map(postEntity,PostDto.class);
    }
}
