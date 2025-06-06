package org.cbioportal.legacy.persistence.mybatis;

import java.util.List;
import org.cbioportal.legacy.model.Sample;
import org.cbioportal.legacy.model.meta.BaseMeta;

public interface SampleMapper {

  List<Sample> getSamples(
      List<String> studyIds,
      String patientId,
      List<String> sampleIds,
      String keyword,
      String projection,
      Integer limit,
      Integer offset,
      String sortBy,
      String direction);

  List<Sample> getSamplesBySampleListIds(List<String> sampleListIds, String projection);

  BaseMeta getMetaSamples(
      List<String> studyIds, String patientId, List<String> sampleIds, String keyword);

  BaseMeta getMetaSamplesBySampleListIds(List<String> sampleListIds);

  Sample getSample(String studyId, String sampleId, String projection);

  List<Sample> getSamplesByInternalIds(List<Integer> internalIds, String projection);

  List<Sample> getSamplesOfPatients(String studyId, List<String> patientIds, String projection);

  List<Sample> getSamplesOfPatientsInMultipleStudies(
      List<String> studyIds, List<String> patientIds, String projection);
}
