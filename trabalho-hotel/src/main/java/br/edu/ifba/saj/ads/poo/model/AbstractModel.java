package br.edu.ifba.saj.ads.poo.model;
import java.time.LocalDateTime;

public abstract class AbstractModel<T> {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Long getId(){
        return id;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt; 
    }

    public AbstractModel(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
