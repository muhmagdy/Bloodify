package com.bloodify.backend.Chat.dto.mapper;

public interface Mapper<Request, Dto, Entity> {
    

    public Dto requestToDto(Request request);

    public Entity dtoToEntity(Dto dto) throws Exception;

    public Request dtoToRequest(Dto dto);

    public Dto entityToDto(Entity entity);
}
