package br.edu.ifba.saj.ads.poo.model;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractModel<T>{
    private T id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Usuario createdBy;
    private Usuario updatedBy;
    private Usuario deletedBy;

    public T getId(){
        return id;
    }

    public void setId(T id){
        this.id = id;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt(){
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt){
        this.deletedAt = deletedAt;
    }

    public Usuario getCreatedBy(){
        return createdBy;
    }

    public void setCreatedBy(Usuario createdBy){
        this.createdBy = createdBy;
    }

    public Usuario getUpdatedBy(){
        return updatedBy;
    }

    public void setUpdatedBy(Usuario updatedBy){
        this.updatedBy = updatedBy;
    }

    public Usuario getDeletedBy(){
        return deletedBy;
    }

    public void setDeletedBy(Usuario deletedBy){
        this.deletedBy = deletedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        AbstractModel<?> outro = (AbstractModel<?>) o;
        return Objects.equals(id, outro.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + "{id=" + id + "}";
    }
}