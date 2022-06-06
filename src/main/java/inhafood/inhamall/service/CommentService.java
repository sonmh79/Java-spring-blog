package inhafood.inhamall.service;

import inhafood.inhamall.domain.Comment;
import inhafood.inhamall.domain.Timestamps;
import inhafood.inhamall.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Long save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment findOne(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public void update(Long commentId, String description) {
        Comment findComment = commentRepository.findById(commentId);
        findComment.setDescription(description);
        Timestamps findTimestamps = findComment.getTimestamps();
        findComment.setTimestamps(new Timestamps(findTimestamps.getCreatedDate(), LocalDateTime.now(),null));
        commentRepository.save(findComment);
    }

    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId);
        comment.destroy();
        commentRepository.delete(comment);
    }

    public List<Comment> findCommentsByArticle(Long articleId) {
        return commentRepository.findByArticle(articleId);
    }

}
