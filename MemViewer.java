import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.image.BufferedImage;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.LinkedList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FileReader;
import java.awt.event.*;

public class MemViewer
{
    int[][] colors_7800 = {{0, 0, 0}, {13, 13, 13}, {40, 40, 40}, {62, 62, 62}, {82, 82, 82}, {101, 101, 101}, {119, 119, 119}, {136, 136, 136}, {152, 152, 152}, {168, 168, 168}, {183, 183, 183}, {198, 198, 198}, {213, 213, 213}, {227, 227, 227}, {241, 241, 241}, {255, 255, 255}, {89, 38, 0}, {105, 58, 0}, {121, 77, 0}, {137, 94, 0}, {151, 110, 0}, {166, 126, 0}, {180, 141, 0}, {193, 156, 0}, {207, 170, 0}, {220, 184, 0}, {233, 197, 0}, {245, 211, 0}, {255, 224, 0}, {255, 236, 0}, {255, 249, 30}, {255, 255, 52}, {135, 0, 0}, {150, 30, 0}, {164, 51, 0}, {178, 70, 0}, {192, 88, 0}, {205, 105, 0}, {218, 121, 0}, {231, 136, 0}, {244, 151, 0}, {255, 165, 0}, {255, 179, 0}, {255, 193, 25}, {255, 206, 47}, {255, 219, 67}, {255, 232, 85}, {255, 245, 101}, {159, 0, 0}, {173, 0, 0}, {187, 25, 0}, {200, 48, 0}, {213, 67, 0}, {226, 85, 0}, {239, 102, 28}, {252, 118, 50}, {255, 133, 69}, {255, 148, 87}, {255, 162, 104}, {255, 177, 120}, {255, 190, 135}, {255, 204, 150}, {255, 217, 164}, {255, 230, 178}, {158, 0, 37}, {172, 0, 58}, {186, 3, 76}, {199, 32, 93}, {213, 53, 110}, {226, 72, 125}, {238, 89, 141}, {251, 106, 155}, {255, 122, 169}, {255, 137, 183}, {255, 152, 197}, {255, 166, 210}, {255, 180, 223}, {255, 194, 236}, {255, 207, 249}, {255, 220, 255}, {133, 0, 145}, {148, 0, 159}, {163, 0, 173}, {177, 28, 187}, {190, 50, 201}, {204, 69, 214}, {217, 87, 227}, {230, 103, 240}, {243, 119, 252}, {255, 135, 255}, {255, 149, 255}, {255, 164, 255}, {255, 178, 255}, {255, 192, 255}, {255, 205, 255}, {255, 218, 255}, {86, 0, 212}, {103, 0, 225}, {119, 13, 238}, {134, 38, 250}, {149, 59, 255}, {163, 77, 255}, {177, 94, 255}, {191, 111, 255}, {204, 126, 255}, {218, 141, 255}, {230, 156, 255}, {243, 170, 255}, {255, 184, 255}, {255, 198, 255}, {255, 211, 255}, {255, 224, 255}, {10, 0, 242}, {36, 13, 255}, {57, 38, 255}, {75, 58, 255}, {93, 77, 255}, {109, 94, 255}, {125, 110, 255}, {140, 126, 255}, {155, 141, 255}, {169, 156, 255}, {183, 170, 255}, {196, 184, 255}, {210, 197, 255}, {223, 211, 255}, {235, 224, 255}, {248, 237, 255}, {0, 21, 235}, {0, 44, 247}, {0, 64, 255}, {0, 82, 255}, {24, 99, 255}, {47, 115, 255}, {66, 130, 255}, {84, 145, 255}, {101, 160, 255}, {117, 174, 255}, {133, 188, 255}, {148, 201, 255}, {162, 214, 255}, {176, 227, 255}, {190, 240, 255}, {203, 253, 255}, {0, 51, 189}, {0, 70, 203}, {0, 88, 216}, {0, 104, 229}, {0, 120, 242}, {0, 135, 254}, {6, 150, 255}, {33, 165, 255}, {54, 179, 255}, {73, 192, 255}, {91, 206, 255}, {107, 219, 255}, {123, 232, 255}, {138, 244, 255}, {153, 255, 255}, {167, 255, 255}, {0, 72, 108}, {0, 89, 124}, {0, 106, 139}, {0, 122, 153}, {0, 137, 168}, {0, 151, 182}, {0, 166, 195}, {0, 180, 209}, {28, 193, 222}, {50, 207, 234}, {69, 220, 247}, {87, 233, 255}, {104, 245, 255}, {120, 255, 255}, {135, 255, 255}, {150, 255, 255}, {0, 82, 0}, {0, 99, 0}, {0, 115, 22}, {0, 131, 45}, {0, 146, 65}, {0, 160, 83}, {0, 174, 100}, {14, 188, 116}, {39, 202, 131}, {59, 215, 146}, {78, 228, 161}, {95, 241, 175}, {111, 253, 189}, {127, 255, 202}, {142, 255, 215}, {157, 255, 228}, {0, 82, 0}, {0, 99, 0}, {0, 115, 0}, {0, 131, 0}, {0, 146, 0}, {16, 160, 0}, {40, 174, 0}, {60, 188, 0}, {79, 202, 21}, {96, 215, 44}, {112, 228, 64}, {128, 241, 82}, {143, 253, 99}, {157, 255, 115}, {171, 255, 131}, {185, 255, 146}, {0, 72, 0}, {0, 89, 0}, {21, 106, 0}, {44, 122, 0}, {64, 137, 0}, {82, 152, 0}, {99, 166, 0}, {115, 180, 0}, {130, 194, 0}, {145, 207, 0}, {160, 220, 0}, {174, 233, 0}, {188, 246, 13}, {201, 255, 38}, {214, 255, 58}, {227, 255, 77}, {57, 51, 0}, {76, 70, 0}, {93, 88, 0}, {109, 105, 0}, {125, 120, 0}, {140, 136, 0}, {155, 151, 0}, {169, 165, 0}, {183, 179, 0}, {197, 193, 0}, {210, 206, 0}, {223, 219, 0}, {236, 232, 0}, {248, 245, 0}, {255, 255, 23}, {255, 255, 45}, {115, 21, 0}, {130, 44, 0}, {145, 64, 0}, {160, 82, 0}, {174, 99, 0}, {188, 115, 0}, {201, 131, 0}, {214, 146, 0}, {227, 160, 0}, {240, 174, 0}, {253, 188, 0}, {255, 202, 0}, {255, 215, 2}, {255, 228, 31}, {255, 240, 53}, {255, 253, 72}};

    byte[] data;
    int dll;
    JFrame frame;
    JPanel masterPanel;
    JLabel filenameLabel;
    JLabel bitmapScreenLabel;
    JLabel bitmapAddressSpaceLabel;
    JPanel bitmapsContainer;
    JPanel dllPanel;
    JPanel addressSpacePanel;
    String filename;
    File file;
    String path;
    JPanel controlPanel;
    BufferedImage screenImage = new BufferedImage(1280, 960, BufferedImage.TYPE_INT_RGB);
    BufferedImage screenImageClean = new BufferedImage(1280, 960, BufferedImage.TYPE_INT_RGB);
    BufferedImage addressSpaceImage = new BufferedImage(8192+64, 1024, BufferedImage.TYPE_INT_RGB);
    ImageIcon bitmap;
    HashMap<Integer, HorizontalZone> horizontalZones = new HashMap<>();

    LinkedList<HashMap<String, Object>> registers;
    JTextArea horizontalZoneDetail;
    String emptyZoneDetail = "                                                                                   ";

    int registerSetIndex;
    int reg_CTRL;
    int reg_CHARBASE;
    Color reg_BACKGROUND;
    Color[] reg_palette = new Color[24];
    String graphicMode;

    class HorizontalZone {
        public int y;
        public int height;
        public String detail;

        public HorizontalZone(int y, int height, String detail) {
            this.y = y;
            this.height = height;
            this.detail = detail;
        }
    }

    Color getColor(int r, int g, int b) {
        return new Color(r, g, b);
    }

    void resetRegisterSet() {
        registerSetIndex = 0;
        loadNextRegisterSet();
    }

    StringBuilder loadNextRegisterSet() {
        StringBuilder sb = new StringBuilder();
        if (registers.size() <= registerSetIndex) {
            return sb;
        }

        HashMap<String, Object> registerSet = registers.get(registerSetIndex);
        if (registerSet.containsKey("ctrl")) {
            reg_CTRL = (int)registers.get(registerSetIndex).get("ctrl");
            sb.append(String.format("\nCTRL=0x%02X", reg_CTRL));
        }
        if (registerSet.containsKey("charbase")) {
            reg_CHARBASE = (int)registers.get(registerSetIndex).get("charbase");
            sb.append(String.format("\nCHARBASE=0x%04X", reg_CHARBASE));
        }
        if (registerSet.containsKey("background")) {
            int colorIdx = (int)registers.get(registerSetIndex).get("background");
            int[] rgb = colors_7800[colorIdx];
            reg_BACKGROUND = getColor(rgb[0], rgb[1], rgb[2]);
        }
        if (registerSet.containsKey("palette")) reg_palette = (Color[])registers.get(registerSetIndex).get("palette");
        registerSetIndex++;

        return sb;
    }

    boolean ReadJson() throws Exception {
        Matcher matcher = ParseFilename();
        if (matcher == null) return false;
        String filename = "";

        try {
            filename = matcher.group(1).toLowerCase() + ".json";

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filename));
            JSONObject root = (JSONObject)obj;
            if (root.containsKey("dll")) {
                this.dll = Integer.parseInt(((String)root.get("dll")).substring(2), 16);
            } else {
                this.dll = 0x2742;
            }
            if (!root.containsKey("registers")) {
                registers = new LinkedList<>();
                return true;
            }

            JSONArray registersJson = (JSONArray)root.get("registers");
            LinkedList<HashMap<String, Object>> registerList = new LinkedList<>();

            for (int ridx=0; ridx<registersJson.size(); ridx++) {
                HashMap<String, Object> rs = new HashMap<>();
                registerList.add(rs);

                JSONObject registerSet = (JSONObject)registersJson.get(ridx);
                if (registerSet.containsKey("ctrl")) {
                    rs.put("ctrl", ((Long)registerSet.get("ctrl")).intValue());
                }
                if (registerSet.containsKey("charbase")) {
                    rs.put("charbase", Integer.parseInt(((String)(registerSet.get("charbase"))).substring(2), 16));
                }
                if (registerSet.containsKey("background")) {
                    rs.put("background", Integer.parseInt(((String)(registerSet.get("background"))).substring(2), 16));
                }

                if (registerSet.containsKey("palette")) {
                    JSONArray palettesJson = (JSONArray)registerSet.get("palette");
                    Color[] palette = new Color[24];
                    int paletteIdx = 0;
                    for (int i=0; i<palettesJson.size(); i++) {
                        JSONArray paletteJson = (JSONArray)palettesJson.get(i);
                        for (int j=0; j<3; j++) {
                            int color = Integer.parseInt(((String)(paletteJson.get(j))).substring(2), 16);
                            int[] rgb = colors_7800[color];
                            palette[paletteIdx] = getColor(rgb[0], rgb[1], rgb[2]);
                            paletteIdx++;
                        }
                    }
                    rs.put("palette", palette);
                }
            }
            registers = registerList;
            return true;
        } catch (Exception e) {
            System.out.printf("Error reading %s: %s\n", filename, e.getMessage());
            e.printStackTrace();            
            return false;
        }
    }    

    void FindDLL() {
        for (int offset = 0x1800; offset < 0x2800; offset++) {
            int byte0 = data[offset];
            int byte1 = data[offset+1];
            int byte2 = data[offset+2];
            if (byte0 < 0) byte0 += 256;
            if (byte1 < 0) byte1 += 256;
            if (byte2 < 0) byte2 += 256;
            int dl = (byte1 << 8) + byte2;
            if ((byte0 & 0x10) == 0 && dl >= 0x1800 && dl < 0x2800) {
                System.out.printf("DLL: 0x%04X\n", offset);
                offset += 3;
                while (data[offset+1] != 0 || data[offset+2] != 0) {
                    offset += 3;
                }
            }
        }
    }

    private void LoadData(File file) throws Exception {
        this.filename = file.getName().toLowerCase();
        this.path = file.getPath();
        this.reg_BACKGROUND = Color.BLACK;
        this.reg_CHARBASE = 0xC000;
        this.reg_CTRL = 0x10;
        this.data = Files.readAllBytes(file.toPath());
//        FindDLL();

        if (!ReadJson()) {
            int[] paletteDefault = new int[] {
                0xF00, 0x0F0, 0x00F,
                0xFF0, 0xF0F, 0x0FF,
                0xFFF, 0x800, 0x080,
                0x008, 0x880, 0x808,
                0x088, 0x888, 0xF88,
                0x8F8, 0x88F, 0xFF8,
                0xF8F, 0x8FF, 0xF80,
                0xF08, 0x0F8, 0x08F
            };
            for (int i=0; i<24; i++) {
                int r = paletteDefault[i] >> 16;
                int g = (paletteDefault[i] >> 8) & 0xF;
                int b = paletteDefault[i] & 0xF;
                reg_palette[i] = getColor(r, g, b);
            }    
            registers = new LinkedList<>();
        }
        resetRegisterSet();
    }

    public String getGraphicMode(int writeMode) {
        switch(this.reg_CTRL & 0x3) {
            case 0:
                return (writeMode == 0) ? "160A" : "160B";
            case 2:
                return (writeMode == 0) ? "320B" : "320D";
            case 3:
                return (writeMode == 0) ? "320A" : "320C";
        }

        return "160A";
    }

    public int getCharacterWidth() {
        return (this.reg_CTRL & 0x10) == 0 ? 1 : 2;
    }

    public Matcher ParseFilename() {
        Pattern pattern = Pattern.compile("([a-z]+)([0-9]*)\\.(.+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(filename);
        boolean matchFound = matcher.find();
        if (matchFound) return matcher;
        return null;
    }

    public void Refresh() throws Exception
    {
        filenameLabel.setText(filename);

        getImageFromAddressSpace();
        drawDLL();
        drawAddressSpaceBoundaries();

        bitmap = new ImageIcon(screenImage);
        Graphics g = screenImageClean.getGraphics();
        g.drawImage(screenImage, 0, 0, null);
        g.dispose();
        bitmap.getImage().flush();
        bitmapScreenLabel.setIcon(bitmap);

        bitmapScreenLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                System.out.println("Mouse pressed at point: (" + e.getX() + ", " + e.getY() + ")");
                int y = (e.getY() - 3) >> 2;

                if (!horizontalZones.containsKey(y)) {
                    Graphics g = screenImage.getGraphics();
                    g.drawImage(screenImageClean, 0, 0, null);
                    horizontalZoneDetail.setText(emptyZoneDetail);
                    g.dispose();
                } else {
                    HorizontalZone zone = horizontalZones.get(y);
                    Graphics g = screenImage.getGraphics();
                    g.drawImage(screenImageClean, 0, 0, null);
                    g.drawRect(0, zone.y*4, 1280, zone.height*4);
                    horizontalZoneDetail.setText(zone.detail);
                    g.dispose();
                }

                bitmap = new ImageIcon(screenImage);
                bitmap.getImage().flush();
                bitmapScreenLabel.setIcon(bitmap);
            }
        });

        ImageIcon bitmap2 = new ImageIcon(addressSpaceImage);
        bitmap2.getImage().flush();
        bitmapAddressSpaceLabel.setIcon(bitmap2);

    }

    boolean displaySprite320(int x, int y, int width, int height, int gfx, int palette, int holeyDma, String graphicMode, boolean direct) {
        boolean somethingDrawn = false;
        int img_y = y+height-1;
        int gfx_offset = gfx;
        int idx;
        int palette_bit = palette & 4;
        for (int j=0; j<height; j++) {
            int gfx_offset_line = gfx_offset;
            boolean skip = false;
            if (img_y >= 240) {
                skip = true;
            }
            if (holeyDma != 0) {
                int gfx_offset_high = gfx_offset >> 12;
                skip = ((gfx_offset_high == 0x9) || (gfx_offset_high == 0xB) || (gfx_offset_high == 0xD) || (gfx_offset_high == 0xF));
            }

            if (!skip) {
                int img_x = x*2;
                for (int i=0; i<width; i++) {
                    int b = data[gfx_offset];
                    if (b < 0) { b += 256; }

                    int asset_x = gfx_offset % 256;
                    int asset_y = 255 - (gfx_offset >> 8);

                    gfx_offset += 1;
                    int[] pixels;
                    switch (graphicMode) {
                        case "320A":
                            pixels = new int[] { b & 0x80, b & 0x40, b & 0x20, b & 0x10,
                                                b & 0x08, b & 0x04, b & 0x02, b & 0x01 };
                            asset_x *= 8;
                            idx = 0;
                            for (int pixel : pixels) {
                                Color clr = reg_palette[palette*3+1];
                                if (pixel != 0 && img_x < 320) {
                                    Graphics2D g2d = screenImage.createGraphics();
                                    g2d.setColor(clr);
                                    g2d.fillRect(img_x*4, img_y*4, 4, 4);
                                    g2d.dispose();
                                    somethingDrawn = true;
                                }

                                // Updates the address space image
                                Graphics2D g2d = addressSpaceImage.createGraphics();
                                if (pixel == 0) {
                                    g2d.setColor(reg_BACKGROUND);
                                } else {
                                    g2d.setColor(reg_palette[palette*3+1]);
                                }
                                g2d.fillRect((asset_x+idx)*4+64, asset_y*4, 4, 4);
                                g2d.dispose();
                                idx++;

                                img_x += 1;
                            }
                            break;
                        case "320B":
                            pixels = new int[] { ((b >> 6) & 2) | ((b >> 3) & 1),
                                                ((b >> 5) & 2) | ((b >> 2) & 1),
                                                ((b >> 4) & 2) | ((b >> 1) & 1),
                                                ((b >> 3) & 2) | ((b) & 1) };
                            asset_x *= 4;
                            idx = 0;
                            for (int pixel : pixels) {
                                Color clr;
                                if (pixel != 0 && img_x < 320) {
                                    clr = reg_palette[palette*3+pixel-1];
                                    Graphics2D g2d = screenImage.createGraphics();
                                    g2d.setColor(clr);
                                    g2d.fillRect(img_x*4, img_y*4, 4, 4);
                                    g2d.dispose();
                                    somethingDrawn = true;
                                }

                                // Updates the address space image
                                Graphics2D g2d = addressSpaceImage.createGraphics();
                                if (pixel == 0) {
                                    g2d.setColor(reg_BACKGROUND);
                                } else {
                                    g2d.setColor(reg_palette[palette*3+pixel-1]);
                                }
                                g2d.fillRect((asset_x+idx)*4+64, asset_y*4, 4, 4);
                                g2d.dispose();
                                
                                img_x += 1;
                                idx++;
                            }
                            break;
                        case "320C":
                            pixels = new int[] { (b & 0x80), (b & 0x40), (b & 0x20), (b & 0x10) };
                            int[] pixelsPalette = { (palette_bit | ((b >> 2) & 3)), (palette_bit | ((b >> 2) & 3)), (palette_bit | (b & 3)), (palette_bit | (b & 3)) };
                            idx = 0;
                            for (int p=0; p<4; p++) {
                                int pixel = pixels[p];
                                if (pixel != 0 && img_x < 320) {
                                    Color clr = reg_palette[3*pixelsPalette[i]];
                                    Graphics2D g2d = screenImage.createGraphics();
                                    g2d.setColor(clr);
                                    g2d.fillRect(img_x*4, img_y*4, 4, 4);
                                    g2d.dispose();
                                    somethingDrawn = true;
                                }

                                // Updates the address space image
                                Graphics2D g2d = addressSpaceImage.createGraphics();
                                if (pixel == 0) {
                                    g2d.setColor(reg_BACKGROUND);
                                } else {
                                    g2d.setColor(reg_palette[3*pixelsPalette[i]]);
                                }
                                g2d.fillRect((asset_x+idx)*4+64, asset_y*4, 4, 4);
                                g2d.dispose();

                                img_x += 2;
                                idx++;
                            }
                            break;
                        case "320D":
                            break;
                        default:
                            break;
                    }
                }
            }
            img_y -= 1;
            gfx_offset = gfx_offset_line + 256;
        }
        return somethingDrawn;
    }

    boolean displaySprite160(int x, int y, int width, int height, int gfx, int palette, int holeyDma, String graphicMode, boolean direct) {
        boolean somethingDrawn = false;
        int img_y = y+height-1;
        int gfx_offset = gfx;

        for (int j=0; j<height; j++) {
            int gfx_offset_line = gfx_offset;
            boolean skip = false;
            if (img_y >= 240) {
                skip = true;
            }
            if (holeyDma == 8) {
                if (height == 8) {
                    int gfx_offset_high = gfx_offset & 0x800;
                    if ((gfx_offset > 0x8000) & (gfx_offset_high == 0x800)) {
                        skip = true;
                    }
                }
            } else if (holeyDma == 16) {
                int gfx_offset_high = gfx_offset >> 12;
                if ((gfx_offset_high == 0x9) || (gfx_offset_high == 0xB) || (gfx_offset_high == 0xD) || (gfx_offset_high == 0xF)) {
                    skip = true;
                }
            }

            if (!skip) {
                int img_x = x;
                int[] pixels;
                for (int i=0; i<width; i++) {
                    int b = gfx_offset < 65536 ? data[gfx_offset] : 0;
                    if (b < 0) b += 256;

                    int asset_x = gfx_offset % 256;
                    int asset_y = 255 - (gfx_offset >> 8);

                    gfx_offset += 1;
                    if (graphicMode == "160A") {
                        pixels = new int[] { b >> 6, (b >> 4) & 3, (b >> 2) & 3, b & 3 };
                        asset_x *= 4;
                    } else if (graphicMode == "160B") {
                        pixels = new int[] { (b >> 6) | ((b) & 0x0C), ((b << 2) & 0x0C) | ((b >> 4) & 3)};
                        asset_x *= 2;
                    } else {
                        System.out.printf("ERROR: mode %s not supported", graphicMode);
                        pixels = new int[] {};
                    }
                    int idx=0;
                    Color clr = null;
                    for (int pixel : pixels) {
                        if (graphicMode == "160A") {
                            if (pixel > 0) {
                                clr = reg_palette[palette*3 + pixel-1];
                            } else {
                                clr = null;
                            }
                        } else if (graphicMode == "160B") {
                            Color[] palette160B = {
                                null, reg_palette[0], reg_palette[1], reg_palette[2],
                                null, reg_palette[3], reg_palette[4], reg_palette[5],
                                null, reg_palette[6], reg_palette[7], reg_palette[8],
                                null, reg_palette[9], reg_palette[10], reg_palette[11],
                            };
                            clr = palette160B[pixel];
                        }
                        if (clr != null && img_x >= 0 && img_x < 160) {
                            Graphics2D g2d = screenImage.createGraphics();
                            g2d.setColor(clr);
                            g2d.fillRect((img_x)*8, img_y*4, 8, 4);
                            g2d.dispose();
                            somethingDrawn = true;
                        }

                        // Updates the address space image
                        Graphics2D g2d = addressSpaceImage.createGraphics();
                        if (clr == null) {
                            g2d.setColor(reg_BACKGROUND);
                        } else {
                            g2d.setColor(clr);
                        }
                        g2d.fillRect((asset_x+idx)*8+64, asset_y*4, 8, 4);
                        g2d.dispose();

                        img_x++;
                        idx ++;
                    }
                }
            }
            img_y -= 1;
            gfx_offset = gfx_offset_line + 256;
        }
        return somethingDrawn;
    }

    void displayDirect(int x, int y, int width, int height, int gfx, int palette, int holeyDMA, String graphicMode) {
        if (graphicMode.startsWith("160")) {
            displaySprite160(x, y, width, height, gfx, palette, holeyDMA, graphicMode, true);
        } else {
            displaySprite320(x, y, width, height, gfx, palette, holeyDMA, graphicMode, true);
        }
    }

    void displayIndirect(int x, int y, int nbTiles, int height, int gfx, int palette, int holeyDMA, String graphicMode) {
        for (int i=0; i<nbTiles; i++) {
            int tileId = data[gfx+i];
            if (tileId < 0) tileId += 256;
            int bytePerSprite = getCharacterWidth();
            if (graphicMode.startsWith("160")) {
                displaySprite160(x+4*bytePerSprite*i, y, bytePerSprite, height, reg_CHARBASE+tileId, palette, holeyDMA, graphicMode, false);
            } else {
                displaySprite320(x+4*i, y, bytePerSprite, height, reg_CHARBASE+tileId, palette, holeyDMA, graphicMode, false);
            }
        }
    }

    StringBuilder[] drawDL(int offset, int height, int y, int holeyDMA) {
        Graphics2D g2d = screenImage.createGraphics();
        g2d.setColor(reg_BACKGROUND);
        g2d.fillRect(0, y*4, 1280, height*4);
        g2d.dispose();

        StringBuilder[] sb = { new StringBuilder(), new StringBuilder() };
        int byte1 = data[offset+1];
        int byte0, byte2, byte3, byte4;
        boolean hasAdded = false;
        int x, palette;
        String mode, graphMode;

        if (byte1 < 0) byte1 += 256;
        while (byte1 != 0) {
            byte0 = data[offset];
            byte2 = data[offset+2];
            byte3 = data[offset+3];
            if (byte0 < 0) byte0 += 256;
            if (byte2 < 0) byte2 += 256;
            if (byte3 < 0) byte3 += 256;

            int gfx = (byte2 << 8) | (byte0);
            int width = byte1 & 0x1F;

            if (hasAdded) {
                sb[0].append("\n");
                sb[1].append("\n");
            }
            sb[0].append(String.format("0x%04X ", offset));

            // 5-byte DL
            if (width == 0) {
                byte4 = data[offset+4];
                if (byte4 < 0) byte4 += 256;
                x = byte4;
                if (x > 200) x -= 256;

                mode = (byte1 & 0x20) != 0 ? "indirect" : "direct  ";
                palette = byte3 >> 5;
                width = 32 - (byte3 & 0x1F);
                if (width == 0) width = 32;
                graphMode = getGraphicMode(mode == "indirect" ? 0 : byte1 >> 7);

                sb[0].append(String.format("[%02X %02X %02X %02X %02X]", byte0, byte1, byte2, byte2, byte4));
                if (mode.startsWith("direct")) {
                    displayDirect(x, y, width, height, gfx, palette, holeyDMA, graphMode);
                } else {
                    displayIndirect(x, y, width, height, gfx, palette, holeyDMA, graphMode);
                }
                offset += 5;
            // 4-byte DL
            } else {
                mode = "direct  ";
                graphMode = getGraphicMode(0);
                // For some reason, Bentley Bear has 160B sprites using a 4-byte DLL
                // Haven't been able to figure out how
                if (filename.startsWith("bentley")) {
                    graphMode = "160B";
                }
                x = byte3;
                if (x > 200) x -= 256;
                palette = byte1 >> 5;
                width = 32 - (byte1 & 0x1F);
                sb[0].append(String.format("[%02X %02X %02X %02X   ]", byte0, byte1, byte2, byte2));
                displayDirect(x, y, width, height, gfx, palette, holeyDMA, graphMode);
                offset += 4;
            }

            String holeyDMAString;
            switch(holeyDMA) {
                case 8:
                    holeyDMAString = ", holeyDMA=8 ";
                    break;
                case 16:
                    holeyDMAString = ", holeyDMA=16";
                    break;
                default:
                    holeyDMAString = "             ";
            }
            sb[0].append(String.format(" x=%03d, GFX=0x%04X, width=%2d, palette=%d, mode=%s, graph mode=%s%s", x, gfx, width, palette, mode, graphMode, holeyDMAString));
            sb[1].append(String.format("x=%03d, GFX=0x%04X, width=%2d, palette=%d, mode=%s, graph mode=%s%s", x, gfx, width, palette, mode, graphMode, holeyDMAString));

            hasAdded = true;

            byte1 = data[offset+1];
            if (byte1 < 0) byte1 += 256;
        }

        if (!hasAdded) {
            sb[1].append(emptyZoneDetail);
        }

        return sb;
    }

    JPanel drawDLL() {
        resetRegisterSet();
        horizontalZones.clear();
        if (data == null) {
            return new JPanel();
        }
        int offset = dll;
        
        while (data[offset] != 0) {
            offset += 3;
        }

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        dllPanel.removeAll();
        dllPanel.setLayout(layout);

        offset = dll;
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 14);
        int y = 0;
        int dl_idx = 0;
        while (data[offset] != 0 && y < 240) {
            int b1 = data[offset];
            int b2 = data[offset+1];
            int b3 = data[offset+2];
            
            if (b1 < 0) b1 += 256;
            if (b2 < 0) b2 += 256;
            if (b3 < 0) b3 += 256;
            int height = (b1 & 15) + 1;
            boolean dli = (b1 & 0x80) != 0;
            StringBuilder sb = new StringBuilder();
            if (dli) {
                sb = loadNextRegisterSet();
            }            

            JTextArea ta = new JTextArea();
            ta.setEditable(false);
            ta.setFont(font);
            String initParams = dl_idx == 0 ?
                String.format("\nCTRL=0x%02X, CHARBASE=0x%04X", reg_CTRL, reg_CHARBASE) : "";
            ta.setText(String.format("Height=%d, y=%d %s\n0x%04X [%02X %02X %02X]%s%s",
                                    height, y, dli ? "DLI" : "", offset, b1, b2, b3, sb.toString(), initParams));
            ta.setBorder(border);
            gbc.gridx = 0;
            gbc.gridy = dl_idx;
            dllPanel.add(ta, gbc);

            int dl_offset = (b2 << 8) | b3;
            int holeyDMA = 0;
            if ((b1 & 0x20) != 0) { holeyDMA = 8; }
            else if ((b1 & 0x40) != 0) { holeyDMA = 16; }


            StringBuilder[] sbs = drawDL(dl_offset, height, y, holeyDMA);
            ta = new JTextArea();
            ta.setEditable(false);
            ta.setFont(font);
            ta.setText(sbs[0].toString());
            ta.setBorder(border);

            HorizontalZone zone = new HorizontalZone(y, height, sbs[1].toString());
            for (int zone_y = 0; zone_y < height; zone_y++) {
                horizontalZones.put(y + zone_y, zone);
            }

            gbc.gridx = 1;
            gbc.gridy = dl_idx;
            dllPanel.add(ta, gbc);
            dl_idx++;

            y += height;
            offset += 3;

        }



        return dllPanel;
    }

    BufferedImage getImageFromAddressSpace() {
        int y = 255;
        for (int offset=0; offset<65536; offset += 256) {
            int x = 0;
            for (int i=0; i<256; i++) {
                int b = data[offset+i];
                if (b < 0) { b += 256; }
                int[] pixels = { b & 0xC0, (b & 0x30) << 2, (b & 0x0C) << 4, (b & 0x3) << 6 };
                for (int x2 = 0; x2<4; x2++) {
                    Color clr = new Color(pixels[x2], pixels[x2], pixels[x2]);
                    Graphics2D g2d = addressSpaceImage.createGraphics();
                    g2d.setColor(clr);
                    g2d.fillRect((x+x2)*8+64, y*4, 8, 4);
                    g2d.dispose();
                }
                x += 4;
            }
            y--;
        }

        return addressSpaceImage;
    }

    void drawAddressSpaceBoundaries() {
        Graphics2D g2d = addressSpaceImage.createGraphics();
        g2d.setColor(Color.WHITE);

        // Draw lines
        int y = 64;
        for (int i=0; i<13; i++) {
            g2d.drawLine(64, y, 8191+64, y);
            y += 64;
        }

        y -= 32;
        for (int i=0; i<2; i++) {
            g2d.drawLine(64, y, 8191+64, y);
            y += 64;
        }

        // Draw holeyDMA regions (16-lines)
        y = 0;
        g2d.setColor(Color.gray);
        for (int i=0; i<4; i++) {
            g2d.fillRect(0, y, 64, 64);
            y += 128;
        }

        // Display addresses
        g2d.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.PLAIN, 17);
        g2d.setFont(font);

        int address = 0xF000;
        y = 70;
        for (int i=0; i<13; i++) {
            String text = String.format("0x%04X", address);
            g2d.drawString(text, 2, y);
            address -= 0x1000;
            y += 64;
        }

        address = 0x2800;
        y -= 32;
        for (int i=0; i<2; i++) {
            String text = String.format("0x%04X", address);
            g2d.drawString(text, 2, y);
            address -= 0x1000;
            y += 64;
        }

        g2d.drawString("RAM", 10, y-32-64);

        g2d.dispose();
    }

    private static File openFile() {
        final JFileChooser fc = new JFileChooser(".");
        fc.setFileFilter(new FileNameExtensionFilter("Memory Dump", "dmp"));
        fc.setPreferredSize(new Dimension(800, 400));
        int returnVal = fc.showOpenDialog(null);
        if (returnVal != 0) return null;
        return fc.getSelectedFile();
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // CREATE MEMVIEWER WINDOW
    ////////////////////////////////////////////////////////////////////////////////////////

    public MemViewer(File file) throws Exception {
        this.frame = new JFrame("7800 Mem Viewer");
        this.masterPanel = new JPanel();
        masterPanel.setPreferredSize(new Dimension(2000, 1120));
        
        frame.add(masterPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );        

        SetComponents();

        LoadData(file);
    }

    public void SetComponents() {
        masterPanel.removeAll();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.LINE_AXIS));
        masterPanel.add(filePanel);
        filenameLabel = new JLabel(filename);
        filePanel.add(filenameLabel);
        filePanel.add(new JLabel(" "));

        Icon icon = UIManager.getIcon("Tree.openIcon");
        JButton loadButton = new JButton(icon);
        loadButton.setMargin(new Insets(0, 0, 0, 0));

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = openFile();
                if (file == null) return;
                try {
                    LoadData(file);
                    Refresh();
                    frame.pack();
                } catch (Exception ex) {
                    System.out.printf("Error: %s\n", ex.getMessage());
                }
            }
            
        });
        filePanel.add(loadButton);
  
        ImageIcon bitmap = new ImageIcon();
        bitmapScreenLabel = new JLabel();
        bitmapScreenLabel.setIcon(bitmap);
        JPanel bitmapScreenPanel = new JPanel();
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 10;
        bitmapScreenPanel.setLayout(layout);

        gbc.gridx = 0;
        gbc.gridy = 0;
        bitmapScreenPanel.add(bitmapScreenLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        horizontalZoneDetail = new JTextArea();
        horizontalZoneDetail.setEditable(false);
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 14);
        horizontalZoneDetail.setFont(font);
        horizontalZoneDetail.setText(emptyZoneDetail);
        bitmapScreenPanel.add(horizontalZoneDetail, gbc);
        JScrollPane bitmapScreenScroll = new JScrollPane(bitmapScreenPanel);
        bitmapScreenScroll.setPreferredSize(new Dimension(1880,1000));
        bitmapScreenScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        bitmapScreenScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        ImageIcon bitmap2 = new ImageIcon();
        bitmapAddressSpaceLabel = new JLabel(bitmap2);

        dllPanel = new JPanel();
        JScrollPane dllScroll = new JScrollPane(dllPanel);
        dllScroll.setPreferredSize(new Dimension(1880,1000));
        dllScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dllScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollPane addressSpaceScroll = new JScrollPane(bitmapAddressSpaceLabel);
        addressSpaceScroll.setPreferredSize(new Dimension(1880,1000));
        addressSpaceScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        addressSpaceScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        tabbedPane.addTab("Screen", bitmapScreenScroll);
        tabbedPane.addTab("DLL", dllScroll);
        tabbedPane.addTab("Address Space", addressSpaceScroll);
        masterPanel.add(tabbedPane);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // AT STARTUP
    ////////////////////////////////////////////////////////////////////////////////////////

    private static void createAndShowGUI() throws Exception
    {
        File file = openFile();
        if (file == null) return;
        
        MemViewer memViewer = new MemViewer(file);
        memViewer.Refresh();
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try {
                    createAndShowGUI();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    for (StackTraceElement ste : e.getStackTrace()) {
                        System.out.println(ste);
                    }
                }
            }
        });
    }
}
