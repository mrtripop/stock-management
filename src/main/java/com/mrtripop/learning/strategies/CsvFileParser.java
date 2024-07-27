package com.mrtripop.learning.strategies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(FileType.CSV)
public class CsvFileParser implements FileParser {
  @Override
  public void parse(String file) {
    log.info("Parsing CSV file: {}", file);
  }
}
