package hh.backend.littlefreelibrary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.backend.littlefreelibrary.domain.Post;
import hh.backend.littlefreelibrary.domain.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PostController {

    // Database repository
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // CRUD operations
    // display post list
    @GetMapping("/post/list")
    public String listPosts(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "postlist";
        // TODO: create postlist.html and add functionality to it
    }
    
    // add a post form
    @GetMapping("/post/new")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "postform";
        // TODO: create postform.html and add functionality to it
    }
    
    // Save new post
    @GetMapping("/post/save")
    public String savePost(Post post) {
        postRepository.save(post);
        return "redirect:/post/list";
    }

    // Delete post
    @GetMapping("/post/{id}/delete")
    public String deletePost(@PathVariable("id") Integer id) {
        postRepository.deleteById(id);
        return "redirect:/post/list";
    }

    // Edit post form
    @GetMapping("/post/{id}/edit")
    public String showEditPostForm(@PathVariable("id") Integer id, Model model) {
        Post post = postRepository.findById(id).orElse(null);
        // If post not found, redirect to list
        if (post == null) {
            return "redirect:/post/list";
        }
        model.addAttribute("post", post);
        return "postform";
    }

}
