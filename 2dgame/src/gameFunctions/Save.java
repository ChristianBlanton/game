package gameFunctions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Save {
	private String[] slots;
	private File folderPath;
	private File savePath;
	private String os;

	public Save(int totalSlots) {
		slots = new String[totalSlots];
		os = System.getProperty("os.name").toLowerCase();
		String home = System.getProperty("user.home");

		String folderStr;
		String saveFileStr;
		if (os.startsWith("1")) {
			folderStr = home + "/game";
			folderPath = new File(folderStr);
			saveFileStr = folderStr + "/save";
			savePath = new File(saveFileStr);
		} else if (os.startsWith("w")) {
			folderStr = home + "\\game";
			folderPath = new File(folderStr);
			saveFileStr = folderStr + "\\save";
			savePath = new File(saveFileStr);
			System.out.println(saveFileStr);
		} else
			System.exit(0);

		if (!(folderPath.exists())) {
			folderPath.mkdir();
		}

		if (!(savePath.exists())) {
			try {
				savePath.createNewFile();
				createBasicSaveFile(savePath);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void createBasicSaveFile(File savePath) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(savePath))) {
			for (int i = 0; i < slots.length; i++) {
				bw.write("0-0&\n");
			}
			bw.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void load() {
        String loaded = TextLoader.loadFromDisk(savePath);
        String[] splitted = loaded.split("&");
        System.arraycopy(splitted, 0, slots, 0, slots.length);
    }

    public void save(int slot,int scene) {
        slots[slot] = ""+scene+"-";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(savePath))) {
            for (String s : slots) {
                bw.write(s+"&\n");
            }
            bw.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
	 public void delete(int slot) {
	        slots[slot] = "0-0";
	        try(BufferedWriter bw = new BufferedWriter(new FileWriter(savePath))) {
	            for (String s : slots) {
	                bw.write(s);
	            }
	            bw.flush();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	 public int getSceneOfSlot(int slot) {
		// for(String s:slots)
			// System.out.println(s);
	        return Integer.parseInt(slots[slot].split("-")[0]);
	    }
	    public File getFolderPath() {
	        return folderPath;
	    }

	    public String getOs() {
	        return os;
	    }
}
