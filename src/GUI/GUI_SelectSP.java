package GUI;



import DAO.SanPham_DAO;
import Entity.LoaiSanPham;
import Entity.SanPham;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI_SelectSP extends JDialog {
public static SanPham sanPhamTimDuoc = null;
    

    public GUI_SelectSP() {
        addControls();
        addEvents();

        this.setSize(650, 450);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JTextField txtTuKhoa;
    private JTable tblSanPham;
    private DefaultTableModel dtmSanPham;
    private JButton btnChon, btnThem;

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        Font font = new Font("Tahoma", Font.PLAIN, 16);
        JPanel pnTop = new JPanel();
        JLabel lblTuKhoa = new JLabel("Từ khoá tìm");
        txtTuKhoa = new JTextField(20);
        lblTuKhoa.setFont(font);
        txtTuKhoa.setFont(font);
        pnTop.add(lblTuKhoa);
        pnTop.add(txtTuKhoa);
        con.add(pnTop, BorderLayout.NORTH);

        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        dtmSanPham = new DefaultTableModel();
        dtmSanPham.addColumn("Mã SP");
        dtmSanPham.addColumn("Tên Sản Phẩm");
        dtmSanPham.addColumn("Loại Sản Phẩm");
        dtmSanPham.addColumn("Số Lượng Còn");
        dtmSanPham.addColumn("Đơn Giá");
        tblSanPham = new JTable(dtmSanPham);
        JScrollPane scrKhachHang = new JScrollPane(tblSanPham);
        pnTable.add(scrKhachHang, BorderLayout.CENTER);
        con.add(pnTable, BorderLayout.CENTER);

        JPanel pnButton = new JPanel();
        btnChon = new JButton("Chọn");
        btnThem = new JButton("Thêm sản phẩm");
        btnChon.setFont(font);
        btnThem.setFont(font);
        pnButton.add(btnChon);
        pnButton.add(btnThem);
        con.add(pnButton, BorderLayout.SOUTH);

        btnChon.setPreferredSize(new Dimension(120, 40));
        btnThem.setPreferredSize(new Dimension(150, 40));

        loadDataLenTable();
    }

    private void addEvents() {
        txtTuKhoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenTable(txtTuKhoa.getText());
            }
        });

        btnChon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyChonSanPham();
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemSanPham();
            }
        });
    }

    private void xuLyChonSanPham() {
        int row = tblSanPham.getSelectedRow();
        if (row > -1) {
            String ma = tblSanPham.getValueAt(row, 0) + "";
            String ten = tblSanPham.getValueAt(row, 1) + "";
            String loai = tblSanPham.getValueAt(row, 2) + "";
            int soLuong = Integer.parseInt(tblSanPham.getValueAt(row, 3) + "");
            double donGia = Double.parseDouble(tblSanPham.getValueAt(row, 4) + "");

            sanPhamTimDuoc = new SanPham(ma, ten, new LoaiSanPham(loai), donGia, soLuong);
            
        }
        this.dispose();
    }

    private void xuLyThemSanPham() {
        
    }

    private void loadDataLenTable() {
        SanPham_DAO ds = new SanPham_DAO();
        List<SanPham> list = ds.docTuBang();
        for (SanPham sp : list){
            String [] rowData={sp.getMaSP(),sp.getTenSP(),sp.getLoaiSP().getMaLoai(),sp.getSoLuong()+"",sp.getGiaBan()+""};
                dtmSanPham.addRow(rowData);
        }
         tblSanPham.setModel(dtmSanPham);
    }

    private void loadDataLenTable(String tuKhoa) {
        
    }

}
