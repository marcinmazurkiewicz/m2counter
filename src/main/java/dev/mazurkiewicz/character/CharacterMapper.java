package dev.mazurkiewicz.character;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CharacterMapper {
    public Character mapRequestToEntity(CharacterRequest request) {
        Character result = new Character();
        result.setName(request.getName());
        result.setDescription(request.getDescription());
        return result;
    }

    public CharacterResponse mapEntityToResponse(Character entity) {
        return new CharacterResponse(entity.getId(), entity.getName(), entity.getDescription());
    }
}
