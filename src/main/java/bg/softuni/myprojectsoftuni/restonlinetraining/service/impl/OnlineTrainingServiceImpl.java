package bg.softuni.myprojectsoftuni.restonlinetraining.service.impl;


import bg.softuni.myprojectsoftuni.restonlinetraining.model.dto.AddOnlineTrainingDto;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.dto.OnlineTrainingByCategoryDto;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.dto.TrainingDto;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.entity.OnlineTrainingSession;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.enums.TrainingType;
import bg.softuni.myprojectsoftuni.restonlinetraining.repository.OnlineTrainingRepository;
import bg.softuni.myprojectsoftuni.restonlinetraining.service.OnlineTrainingService;
import bg.softuni.myprojectsoftuni.restonlinetraining.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlineTrainingServiceImpl implements OnlineTrainingService {

    private final OnlineTrainingRepository onlineTrainingRepository;

    public OnlineTrainingServiceImpl( OnlineTrainingRepository onlineTrainingRepository) {

        this.onlineTrainingRepository = onlineTrainingRepository;
    }

    @Override
    public TrainingDto createOnlineTraining(AddOnlineTrainingDto addOnlineTrainingDto) {
        OnlineTrainingSession onlineTrainingSession = map(addOnlineTrainingDto);
        onlineTrainingRepository.save(onlineTrainingSession);

      //  OnlineTrainingSession onlineTrainingSession = onlineTrainingRepository.save(modelMapper.map(addOnlineTrainingDto, OnlineTrainingSession.class));

        return map(onlineTrainingSession);


    }

    @Override
    public List<TrainingDto> getAllOnlineTrainings() {
        return onlineTrainingRepository
                .findAll()
                .stream()
                .map(OnlineTrainingServiceImpl::map)
                .toList();

    }

    @Override
    public TrainingDto getOnlineTrainingDetails(Long id) {
        return onlineTrainingRepository.findById(id)
                .map(OnlineTrainingServiceImpl::map)
                .orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public void deleteOnlineTraining(Long id) {
        onlineTrainingRepository.deleteById(id);
    }

    @Override
    public List<OnlineTrainingByCategoryDto> findByType(TrainingType trainingType) {
        return onlineTrainingRepository.findByType(trainingType);
    }

    private static TrainingDto map(OnlineTrainingSession onlineTrainingSession){

        return new TrainingDto(
                onlineTrainingSession.getId(),
                onlineTrainingSession.getCoachName(),
                onlineTrainingSession.getTitle(),
                onlineTrainingSession.getDescription(),
                onlineTrainingSession.getVideoUrl(),
                onlineTrainingSession.getImageUrl(),
                onlineTrainingSession.getType()


        );
    }

    private static OnlineTrainingSession map(AddOnlineTrainingDto addOnlineTrainingDto){
        return new OnlineTrainingSession()
                .setTitle(addOnlineTrainingDto.title())
                .setCoachName(addOnlineTrainingDto.coachName())
                .setType(addOnlineTrainingDto.type())
                .setDescription(addOnlineTrainingDto.description())
                .setVideoUrl(addOnlineTrainingDto.videoUrl())
                .setImageUrl(addOnlineTrainingDto.imageUrl());
    }
}

