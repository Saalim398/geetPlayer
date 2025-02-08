# geetPlayer

g.e.e.t Player is a simple Java-based audio player built using the Java Swing framework and Java Sound API. It allows users to play, pause, and restart audio files in `.wav` format with an interactive graphical user interface (GUI).

## Features
- **Play Audio:** Load and play `.wav` audio files.
- **Pause and Resume:** Pause the audio playback and resume from the same position.
- **Restart:** Restart the audio from the beginning.
- **Seek Bar:** Navigate through the audio using a slider.
- **Graphical Interface:** Simple and elegant UI using Java Swing.
- **File Chooser:** Select audio files using a file dialog.

## Prerequisites
Ensure you have the following installed before running the application:

- Java Development Kit (JDK) 8 or higher
- An Integrated Development Environment (IDE) such as Eclipse, IntelliJ, or NetBeans

## Installation

1. Clone this repository or download the source code:
   ```sh
   git clone https://github.com/yourusername/geetPlayer.git
   ```
2. Open the project in your favorite Java IDE.
3. Ensure that the images (icons) are correctly placed in the `geetPlayer/src/images/` directory.
4. Compile and run the `geetPlayerGUI.java` file.

## Usage

### Opening an Audio File
1. Click the folder icon.
2. Select a `.wav` file from your system.
3. The file name will appear in the UI.

### Playing and Pausing Audio
- Click the play button to start playing.
- Click the play button again to pause the playback.

### Restarting Audio
- Click the restart button to play the audio from the beginning.

### Seeking Audio Position
- Drag the seek bar to navigate to different parts of the audio file.

## Project Structure
```
geetPlayer/
│── src/
│   ├── images/
│   │   ├── iconImage.png
│   │   ├── open-folder.png
│   │   ├── play-button.png
│   │   ├── pause-button.png
│   │   ├── reloading.png
│   │   ├── music.png
│   ├── geetPlayerGUI.java
│── README.md
```

## Code Explanation
The `geetPlayerGUI` class extends `JFrame` to create the main GUI of the application. It includes:
- `JButton` for opening files, playing, pausing, and restarting the audio.
- `JSlider` for the seek bar functionality.
- `JLabel` for displaying the song name and artist name.
- `Clip` and `AudioInputStream` from Java Sound API to handle audio playback.

## Future Improvements
- Support for more audio formats like MP3.
- Add volume control.
- Implement a playlist feature.
- Add better UI styling.

## License
This project is open-source and available under the MIT License.

## Author
Mohammad Saalim Khan

---
Feel free to contribute or report issues to improve the project!

