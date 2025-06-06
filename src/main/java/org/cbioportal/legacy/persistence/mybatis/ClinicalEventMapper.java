package org.cbioportal.legacy.persistence.mybatis;

import java.util.List;
import org.cbioportal.legacy.model.ClinicalEvent;
import org.cbioportal.legacy.model.ClinicalEventData;
import org.cbioportal.legacy.model.meta.BaseMeta;

public interface ClinicalEventMapper {

  List<ClinicalEvent> getPatientClinicalEvent(
      String studyId,
      String patientId,
      String projection,
      Integer limit,
      Integer offset,
      String sortBy,
      String direction);

  BaseMeta getMetaPatientClinicalEvent(String studyId, String patientId);

  List<ClinicalEventData> getDataOfClinicalEvents(List<Long> clinicalEventIds);

  List<ClinicalEvent> getStudyClinicalEvent(
      String studyId,
      String projection,
      Integer limit,
      Integer offset,
      String sortBy,
      String direction);

  BaseMeta getMetaClinicalEvent(String studyId);

  List<ClinicalEvent> getSamplesOfPatientsPerEventType(
      List<String> studyIds, List<String> sampleIds);

  List<ClinicalEvent> getPatientsDistinctClinicalEventInStudies(
      List<String> studyIds, List<String> patientIds);

  List<ClinicalEvent> getTimelineEvents(
      List<String> studyIds, List<String> patientIds, List<ClinicalEvent> clinicalEvents);

  List<ClinicalEvent> getClinicalEventsMeta(
      List<String> studyIds, List<String> patientIds, List<ClinicalEvent> clinicalEvents);
}
