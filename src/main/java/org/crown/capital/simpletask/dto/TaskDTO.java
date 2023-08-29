package org.crown.capital.simpletask.dto;

import lombok.*;
import org.crown.capital.simpletask.enums.TaskStatus;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private TaskStatus status;
    private String note;

}
