package bg.softuni.myprojectsoftuni.restonlinetraining.model.dto;


import bg.softuni.myprojectsoftuni.restonlinetraining.model.enums.TrainingType;

public record TrainingDto(

        Long id,
        String coachName,


        String title,


        String description,


        String videoUrl,


        String imageUrl,
        TrainingType type) {
}