package com.ugWorld.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journalEntry")
@Data
@NoArgsConstructor
//@Getter
//@Setter
public class JournalEntry {
    @Id
    @NonNull
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;

}
