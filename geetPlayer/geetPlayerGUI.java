package geetPlayer;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class geetPlayerGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Clip clip;
    private AudioInputStream as;
    private boolean isPlaying = false;
    private long clipTimePosition = 0;
    private long clipLength;
    private ImageIcon rIcon;
    private JSlider seekBar;
    private JLabel songName;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    geetPlayerGUI frame = new geetPlayerGUI();
                    ImageIcon img = new ImageIcon("geetPlayer\\src\\images\\iconImage.png");
                    frame.setVisible(true);
                    frame.setTitle("geetPlayer");
                    frame.setIconImage(img.getImage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public geetPlayerGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 423, 423);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBackground(new Color(0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Border emptyBorder = BorderFactory.createEmptyBorder();
        songName = new JLabel("SongName");
        songName.setForeground(new Color(255, 255, 255));
        songName.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
        songName.setBounds(88, 175, 253, 26);
        contentPane.add(songName);

        seekBar = new JSlider();
        seekBar.setFont(new Font("Californian FB", Font.PLAIN, 10));
        seekBar.setForeground(new Color(255, 255, 255));
        seekBar.setBackground(new Color(0, 0, 0));
        seekBar.setBounds(98, 238, 200, 26);
        contentPane.add(seekBar);

        ImageIcon openFile = new ImageIcon("geetPlayer\\src\\images\\open-folder.png");
        Image imageFile = openFile.getImage();
        Image fileImage = imageFile.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon fileicon = new ImageIcon(fileImage);
        JButton openSong = new JButton(fileicon);
        openSong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showOpenDialog(contentPane);
                if (response == JFileChooser.APPROVE_OPTION) {
                    File songPath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try {
                        as = AudioSystem.getAudioInputStream(songPath);
                        clip = AudioSystem.getClip();
                        clip.open(as);
                       

                        songName.setText(songPath.getName());
                        clipLength = clip.getMicrosecondLength() / 1000;  
                        seekBar.setValue(0);
                        seekBar.setMinimum(0);
                        seekBar.setMaximum((int) clipLength);
                        
                        seekBar.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent e) {
                                if (!seekBar.getValueIsAdjusting()) {
                                    long newPosition = seekBar.getValue();
                                    clip.setMicrosecondPosition(newPosition * 1000); 
                                }
                            }
                        });
                    } catch (UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(contentPane, "File of unsupported format", "Warning", JOptionPane.WARNING_MESSAGE);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(contentPane, "Something went wrong", "Warning", JOptionPane.WARNING_MESSAGE);
                    } catch (LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        ImageIcon playIcon = new ImageIcon("geetPlayer\\src\\images\\play-button.png");
        Image imagePlay = playIcon.getImage();
        Image newImg1 = imagePlay.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        rIcon = new ImageIcon(newImg1);
        JButton playButton = new JButton(rIcon);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (clip != null) {
                    if (!isPlaying) {
                        playAudio();

                    } else {
                       
                        pauseAudio();
                    }
                }
            }
        });
        playButton.setBorder(emptyBorder);
        playButton.setForeground(new Color(0, 0, 0));
        playButton.setBackground(new Color(0, 0, 0));
        playButton.setFont(new Font("Caladea", Font.PLAIN, 12));
        playButton.setBounds(88, 293, 80, 50);
        contentPane.add(playButton);
        
        
        ImageIcon restartImg = new ImageIcon("geetPlayer\\src\\images\\reloading.png");
        Image imageRestart = restartImg.getImage();
        Image newRestart = imageRestart.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon Icon = new ImageIcon(newRestart);
        JButton restart = new JButton(Icon);
        
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });
        restart.setForeground(new Color(0, 0, 0));
        restart.setBackground(new Color(0, 0, 0));
        
        restart.setBorder(emptyBorder);
        restart.setFont(new Font("Caladea", Font.PLAIN, 12));
        restart.setBounds(178, 293, 80, 50);
        contentPane.add(restart);

        openSong.setBorder(emptyBorder);
        openSong.setForeground(new Color(0, 0, 0));
        openSong.setBackground(new Color(0, 0, 0));
        openSong.setBounds(271, 293, 80, 50);
        contentPane.add(openSong);

        JLabel ArtistName = new JLabel("Artist Name");
        ArtistName.setForeground(new Color(255, 255, 255));
        ArtistName.setFont(new Font("MV Boli", Font.PLAIN, 12));
        ArtistName.setBackground(new Color(255, 255, 255));
        ArtistName.setBounds(88, 201, 116, 27);
        contentPane.add(ArtistName);

        
        
        ImageIcon songImagee = new ImageIcon("geetPlayer\\src\\images\\music.png");
        Image song = songImagee.getImage();
        Image newSong = song.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon musicIcon = new ImageIcon(newSong);
        JLabel songImage = new JLabel();
        songImage.setForeground(Color.WHITE);
        songImage.setBackground(Color.WHITE);
        songImage.setBounds(138, 37, 120, 120);
        songImage.setIcon(musicIcon);
        contentPane.add(songImage);
    }

    private void playAudio() {
        if (clip != null) {
            clip.setMicrosecondPosition(clipTimePosition * 1000);  
            clip.start();
            isPlaying = true;

            
            new Thread(new Runnable() {
                public void run() {
                    while (clip.isRunning()) {
                        try {
                            long currentPosition = clip.getMicrosecondPosition() / 1000;  
                            seekBar.setValue((int) currentPosition);
                            Thread.sleep(100);  
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    private void pauseAudio() {
        if (clip != null) {
            clipTimePosition = clip.getMicrosecondPosition() / 1000;  
            clip.stop();
            isPlaying = false;
            
        }
        ImageIcon playIcon = new ImageIcon("geetPlayer\\src\\images\\play-button.png");
        Image imagePlay = playIcon.getImage();
        Image newImg1 = imagePlay.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        rIcon = new ImageIcon(newImg1);
        
    }

    private void restart() {
        if (clip != null) {
            clip.stop();
            clip.setMicrosecondPosition(0);  
            clip.start();
        }
        
        ImageIcon pauseIcon = new ImageIcon("geetPlayer\\\\src\\\\images\\pause-button.png");
        Image imagePause = pauseIcon.getImage();
        Image newPause = imagePause.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        rIcon = new ImageIcon(newPause);
    }
}
