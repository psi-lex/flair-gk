package com.flair.flair.mapper;

import com.flair.flair.model.NewNoteTo;
import com.flair.flair.model.NoteTo;
import com.flair.flair.persistence.EmployeeEntity;
import com.flair.flair.persistence.NoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    // NewNoteTo <-> NoteEntity
    NoteEntity mapNewNoteToToNoteEntity(NewNoteTo source);
    NewNoteTo mapNoteEntityToNewNoteTo(NoteEntity source);

    // NoteTo <-> EntityNote
    @Mapping(target = "author", source = "author", qualifiedByName = "mapAuthorName")
    NoteTo mapNoteEntityToNoteTo(NoteEntity source);


   // NoteEntity mapNoteToToNoteEntity(NoteTo source);

    @Named("mapAuthorName")
    default String mapAuthorName(EmployeeEntity employeeEntity) {
        if (employeeEntity == null) {
            return null;
        }
        return employeeEntity.getFirstName() + " " + employeeEntity.getLastName();
    }
}
