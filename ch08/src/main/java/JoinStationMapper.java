// cc JoinStationMapper Mapper for tagging station records for a reduce-side join
import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

// vv JoinStationMapper
public class JoinStationMapper extends MapReduceBase
    implements Mapper<LongWritable, Text, TextPair, Text> {
  private NcdcStationMetadataParser parser = new NcdcStationMetadataParser();

  public void map(LongWritable key, Text value,
      OutputCollector<TextPair, Text> output, Reporter reporter)
      throws IOException {

    if (parser.parse(value)) {
      output.collect(new TextPair(parser.getStationId(), "0"),
          new Text(parser.getStationName()));
    }
  }
}
// ^^ JoinStationMapper