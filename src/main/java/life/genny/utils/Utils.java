package life.genny.utils;

import java.util.ArrayList;
import java.util.List;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.FileSystemException;

public class Utils {

	public static List<Buffer> recursiveFilesReader(String folder) {
		List<Buffer> bufs = new ArrayList<Buffer>();
		FileSystem fs = Vertx.vertx().fileSystem();
		List<String> dir = fs.readDirBlocking(folder);
		dir.stream().forEach(fileName -> {
			try {
				bufs.add(fs.readFileBlocking(fileName.toString()));
			}catch(FileSystemException e) {
				bufs.addAll(recursiveFilesReader(fileName.toString()));
			}
		});
		return bufs;
	}
}
