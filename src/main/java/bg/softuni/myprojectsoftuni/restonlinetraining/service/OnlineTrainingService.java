package bg.softuni.myprojectsoftuni.restonlinetraining.service;


import bg.softuni.myprojectsoftuni.restonlinetraining.model.dto.AddOnlineTrainingDto;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.dto.OnlineTrainingByCategoryDto;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.dto.TrainingDto;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.enums.TrainingType;

import java.util.List;

public interface OnlineTrainingService {
    TrainingDto createOnlineTraining(AddOnlineTrainingDto addOnlineTrainingDto);

    List<TrainingDto> getAllOnlineTrainings();

    TrainingDto getOnlineTrainingDetails(Long id);

    void deleteOnlineTraining(Long id);

    List<OnlineTrainingByCategoryDto> findByType(TrainingType trainingType);
}
