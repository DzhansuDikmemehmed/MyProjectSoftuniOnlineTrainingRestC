package bg.softuni.myprojectsoftuni.restonlinetraining.repository;


import bg.softuni.myprojectsoftuni.restonlinetraining.model.dto.OnlineTrainingByCategoryDto;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.entity.OnlineTrainingSession;
import bg.softuni.myprojectsoftuni.restonlinetraining.model.enums.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnlineTrainingRepository extends JpaRepository<OnlineTrainingSession,Long> {
    @Query("SELECT new bg.softuni.myprojectsoftuni.restonlinetraining.model.dto.OnlineTrainingByCategoryDto(ot.id, ot.title, ot.description, ot.imageUrl) FROM OnlineTrainingSession ot WHERE ot.type = :type")
    List<OnlineTrainingByCategoryDto> findByType(@Param("type") TrainingType type);
}
