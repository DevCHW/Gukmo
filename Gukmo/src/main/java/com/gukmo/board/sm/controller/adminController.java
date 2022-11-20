package com.gukmo.board.sm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gukmo.board.model.MemberVO;
import com.gukmo.board.model.PenaltyVO;
import com.gukmo.board.sm.service.InterMemberManageService;

@Controller
public class adminController {
   
   @Autowired   // Type �� ���� �˾Ƽ� Bean �� �������ش�.
   private InterMemberManageService service;
   
      
   // ȸ������ ��� ������ ��û
   @RequestMapping(value="/admin/memberManage_List.do", method= {RequestMethod.GET})  // ������ GET ��ĸ� ����ϴ� ����.
   public ModelAndView requredAdminLogin_memberManageList(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
       List<MemberVO> memberList = null;
       
       String searchType = request.getParameter("searchType");
      String searchWord = request.getParameter("searchWord");
      // System.out.println(searchType);
      // System.out.println(searchWord);
      
      String str_currentShowPageNo = request.getParameter("currentShowPageNo");
      
       if(searchType == null || (!"fk_userid".equals(searchType) && !"nickname".equals(searchType)) ) {
         searchType = "";
       }
      
       if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
          searchWord = "";
       }
       
       Map<String, String> paraMap = new HashMap<>();
       paraMap.put("searchType", searchType);
       paraMap.put("searchWord", searchWord);

       
       // ���� �� �Խù� �Ǽ�(totalCount)�� ���ؿ;� �Ѵ�.
       // �� �Խù� �Ǽ�(totalCount)�� �˻������� ���� ���� �������� ����������.
       int totalCount = 0;           // �� �Խù� �Ǽ�
       int sizePerPage = 10;         // �� �������� ������ �Խù� �Ǽ� 
       int currentShowPageNo = 0;    // ���� �����ִ� ��������ȣ�μ�, �ʱ�ġ�δ� 1�������� ������.
       int totalPage = 0;            // �� ��������(���������󿡼� ������ �� ������ ����, ��������)
         
       int startRno = 0; // ���� ���ȣ
       int endRno = 0;   // �� ���ȣ
       
       // �� �Խù� �Ǽ�(totalCount)
       totalCount = service.getTotalCount(paraMap);
       // System.out.println(totalCount);
       // 108
       
       totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );

       if(str_currentShowPageNo == null) {
          // �Խ��ǿ� �������� �ʱ�ȭ�� 
          currentShowPageNo = 1;
       }
       else {
       
          try {
             currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
             if( currentShowPageNo < 1 || currentShowPageNo > totalPage ) {
                currentShowPageNo = 1;
             }
          } catch(NumberFormatException e) {
             currentShowPageNo = 1;
          }
       
       }       
       
       startRno = ((currentShowPageNo - 1) * sizePerPage) + 1;
       endRno = startRno + sizePerPage - 1;
       
       paraMap.put("startRno", String.valueOf(startRno));
       paraMap.put("endRno", String.valueOf(endRno));

       memberList = service.memberList(paraMap);
       System.out.println();
      
       if( !"".equals(searchType) && !"".equals(searchWord) ) {
          mav.addObject("paraMap", paraMap);
       }
       
         // === #121. �������� ����� === //
         int blockSize = 10;
         // blockSize �� 1�� ��(�丷)�� �������� ��������ȣ�� �����̴�.
         /*
                          1  2  3  4  5  6  7  8  9 10 [����][������]  -- 1����
            [��ó��][����]  11 12 13 14 15 16 17 18 19 20 [����][������]  -- 1����
            [��ó��][����]  21 22 23
         */
         
         int loop = 1;
         int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;

         String pageBar = "<ul class=\"my pagination pagination-md justify-content-center mt-5\">";
         String url = "memberManage_List.do";

         // === [��ó��][����] ����� === //
         if(pageNo != 1) {
            pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo=1'><i class=\"fa-solid fa-angles-left\"></i></a></li>";
            pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'><i class=\"fa-solid fa-angles-left\"></i></a></li>"; 
         }      
         
         while( !(loop > blockSize || pageNo > totalPage) ) {
            
            if(pageNo == currentShowPageNo) {
               pageBar += "<li class=\"page-item active\" aria-current=\"page\">"+pageNo+"</li>";  
            }
            else {
               pageBar += "<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";        
            }
            loop++;
            pageNo++;
         }// end of while--------------------------
         
         // === [����][������] ����� === //
         if( pageNo <= totalPage) {
            pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'><i class=\"fa-solid fa-angle-right\"></i></a></li>";
            pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+totalPage+"'><i class=\"fas fa-solid fa-angles-right\"></i></a></li>";
         }
         
         pageBar += "</ul>";
         
         mav.addObject("pageBar", pageBar);
         mav.addObject("memberList", memberList);

         
      mav.setViewName("admin/memberManage_List.tiles1");
         //   /WEB-INF/views/tiles1/admin/memberManage_List.jsp ������ �����Ѵ�.
      return mav;
   }
   
   
   
   // ȸ�� ���� �󼼺��� 
   @RequestMapping(value="/admin/memberDetail.do", method= {RequestMethod.GET})  // ������ GET ��ĸ� ����ϴ� ����.
   public ModelAndView requredAdminLogin_memberDetail(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
      Map<String, String> paraMap = new HashMap<>();
      String userid = request.getParameter("userid");
      paraMap.put("userid", userid);
      
      MemberVO memberDetail = service.getMemberDetail(paraMap);
      
      mav.addObject("memberDetail", memberDetail);
      mav.setViewName("admin/memberDetail.tiles1");
         //   /WEB-INF/views/tiles1/admin/memberDetail.jsp ������ �����Ѵ�.
      return mav;
   }
   
   // ȸ�� ���� ��� ������
   @RequestMapping(value="/admin/penaltyRegister.do", method= {RequestMethod.GET})  // ������ GET ��ĸ� ����ϴ� ����.
   public ModelAndView requredAdminLogin_penaltyRegister(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
      
      Map<String, String> paraMap = new HashMap<>();
      String userid = request.getParameter("userid");
      String nickname = request.getParameter("nickname");
      
      paraMap.put("userid", userid);
      
      request.setAttribute("userid", userid);
      request.setAttribute("nickname", nickname);
      
       mav.setViewName("admin/penaltyRegister.tiles1");
         //   /WEB-INF/views/tiles1/admin/memberDetail.jsp ������ �����Ѵ�.
      return mav;
   }   
   
   // ȸ�� ���� ��� �Ϸ� ������
   @RequestMapping(value="/admin/penaltyRegisterResult.do", method= {RequestMethod.POST})  // ������ GET ��ĸ� ����ϴ� ����.
   public ModelAndView requredAdminLogin_penaltyRegisterResult(HttpServletRequest request, HttpServletResponse response, ModelAndView mav, PenaltyVO pvo) {
      
      String userid = request.getParameter("userid");
      Map<String,String> paraMap = new HashMap<>();
      
      // tbl_penalty�� �ش� ȸ�� ���� insert
      int n = service.addPenalty(pvo);
            
      if(n == 1 ) {
         
         // tbl_member_login�� �ش� ȸ�� status ����(����)
         int n2 = service.updateMemberStatus(userid);
         
         if(n2 == 1) {
            mav.setViewName("redirect:/admin/memberManage_List.do");
         }
      }
      
      
      return mav;
   }

   // ���� ���� 
   @ResponseBody
   @RequestMapping(value="/admin/block_recovery.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
   public String requredAdminLogin_block_recovery(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
      
      String userid = request.getParameter("userid");

      Map<String, String> paraMap = new HashMap<>();      
      paraMap.put("userid", userid);
      
      int n = service.block_recovery(paraMap);
      JSONObject jsonObj = new JSONObject();
      
      if(n== 1) {
         jsonObj.put("n", n);
      }
      
      return jsonObj.toString(); // "{"n":1,"name":"������"}" �Ǵ� "{"n":0,"name":"������"}"
      
   } //end of ���� ����
   
   
   // �޸� > Ȱ��
   @ResponseBody
   @RequestMapping(value="/admin/sleep_recovery.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
   public String requredAdminLogin_sleep_recovery(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
      
      String userid = request.getParameter("userid");

      Map<String, String> paraMap = new HashMap<>();      
      paraMap.put("userid", userid);
      
      int n = service.sleep_recovery(paraMap);
      JSONObject jsonObj = new JSONObject();
      
      if(n== 1) {
         jsonObj.put("n", n);
      }
      
      return jsonObj.toString(); // "{"n":1,"name":"������"}" �Ǵ� "{"n":0,"name":"������"}"
      
   } //end of ���� ����
   
   
   // ȸ������ ��� ������ ��û
   @RequestMapping(value="/admin/academyManage_List.do", method= {RequestMethod.GET})  // ������ GET ��ĸ� ����ϴ� ����.
   public ModelAndView requredAdminLogin_academyManageList(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
       List<MemberVO> academymemberList = null;
       
       String searchType = request.getParameter("searchType");
      String searchWord = request.getParameter("searchWord");
      // System.out.println(searchType);
      // System.out.println(searchWord);
      
      String str_currentShowPageNo = request.getParameter("currentShowPageNo");
      
       if(searchType == null || (!"fk_userid".equals(searchType) && !"nickname".equals(searchType) && !"academy_name".equals(searchType)) ) {
         searchType = "";
       }
      
       if(searchWord == null || "".equals(searchWord) || searchWord.trim().isEmpty() ) {
          searchWord = "";
       }
       
       Map<String, String> paraMap = new HashMap<>();
       paraMap.put("searchType", searchType);
       paraMap.put("searchWord", searchWord);

       
       // ���� �� �Խù� �Ǽ�(totalCount)�� ���ؿ;� �Ѵ�.
       // �� �Խù� �Ǽ�(totalCount)�� �˻������� ���� ���� �������� ����������.
       int totalCount = 0;           // �� �Խù� �Ǽ�
       int sizePerPage = 10;         // �� �������� ������ �Խù� �Ǽ� 
       int currentShowPageNo = 0;    // ���� �����ִ� ��������ȣ�μ�, �ʱ�ġ�δ� 1�������� ������.
       int totalPage = 0;            // �� ��������(���������󿡼� ������ �� ������ ����, ��������)
         
       int startRno = 0; // ���� ���ȣ
       int endRno = 0;   // �� ���ȣ
       
       // �� �Խù� �Ǽ�(totalCount)
       totalCount = service.getTotalCount_academy(paraMap);
       request.setAttribute("totalCount", totalCount);
       // System.out.println(totalCount);
       // 108
       
       totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );

       if(str_currentShowPageNo == null) {
          // �Խ��ǿ� �������� �ʱ�ȭ�� 
          currentShowPageNo = 1;
       }
       else {
       
          try {
             currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
             if( currentShowPageNo < 1 || currentShowPageNo > totalPage ) {
                currentShowPageNo = 1;
             }
          } catch(NumberFormatException e) {
             currentShowPageNo = 1;
          }
       
       }       
       
       startRno = ((currentShowPageNo - 1) * sizePerPage) + 1;
       endRno = startRno + sizePerPage - 1;
       
       paraMap.put("startRno", String.valueOf(startRno));
       paraMap.put("endRno", String.valueOf(endRno));

       academymemberList = service.academymemberList(paraMap);
      
       if( !"".equals(searchType) && !"".equals(searchWord) ) {
          mav.addObject("paraMap", paraMap);
       }
       
         // === #121. �������� ����� === //
         int blockSize = 10;
         // blockSize �� 1�� ��(�丷)�� �������� ��������ȣ�� �����̴�.
         /*
                          1  2  3  4  5  6  7  8  9 10 [����][������]  -- 1����
            [��ó��][����]  11 12 13 14 15 16 17 18 19 20 [����][������]  -- 1����
            [��ó��][����]  21 22 23
         */
         
         int loop = 1;
         int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;

         String pageBar = "<ul class=\"my pagination pagination-md justify-content-center mt-5\">";
         String url = "academyManage_List.do";

         // === [��ó��][����] ����� === //
         if(pageNo != 1) {
            pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo=1'><i class=\"fa-solid fa-angles-left\"></i></a></li>";
            pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+(pageNo-1)+"'><i class=\"fa-solid fa-angles-left\"></i></a></li>"; 
         }      
         
         while( !(loop > blockSize || pageNo > totalPage) ) {
            
            if(pageNo == currentShowPageNo) {
               pageBar += "<li class=\"page-item active\" aria-current=\"page\">"+pageNo+"</li>";  
            }
            else {
               pageBar += "<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";        
            }
            loop++;
            pageNo++;
         }// end of while--------------------------
         
         // === [����][������] ����� === //
         if( pageNo <= totalPage) {
            pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+pageNo+"'><i class=\"fa-solid fa-angle-right\"></i></a></li>";
            pageBar += "<li class=\"page-item\"><a class=\"page-link\" href='"+url+"?searchType="+searchType+"&searchWord="+searchWord+"&currentShowPageNo="+totalPage+"'><i class=\"fas fa-solid fa-angles-right\"></i></a></li>";
         }
         
         pageBar += "</ul>";
         
         mav.addObject("pageBar", pageBar);
         mav.addObject("academymemberList", academymemberList);

         
      mav.setViewName("admin/academyManage_List.tiles1");
         //   /WEB-INF/views/tiles1/admin/memberManage_List.jsp ������ �����Ѵ�.
      return mav;
   } // end of �п�ȸ�� ����Ʈ ����
   
   // ȸ�� ���� �󼼺��� 
   @RequestMapping(value="/admin/aca_memberDetail.do", method= {RequestMethod.GET})  // ������ GET ��ĸ� ����ϴ� ����.
   public ModelAndView requredAdminLogin_aca_memberDetail(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
      Map<String, String> paraMap = new HashMap<>();
      String userid = request.getParameter("userid");
      paraMap.put("userid", userid);
      
      MemberVO aca_memberDetail = service.getAcademyDetail(paraMap);
      
      mav.addObject("aca_memberDetail", aca_memberDetail);
      mav.setViewName("admin/aca_memberDetail.tiles1");
         //   /WEB-INF/views/tiles1/admin/memberDetail.jsp ������ �����Ѵ�.
      return mav;
   }

   
   // ȸ������ ��û ����
   @ResponseBody
   @RequestMapping(value="/admin/Regi_agree.do", method= {RequestMethod.POST}, produces="text/plain;charset=UTF-8")
   public String requredAdminLogin_Regi_agree(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
      
      String userid = request.getParameter("userid");

      Map<String, String> paraMap = new HashMap<>();      
      paraMap.put("userid", userid);
      
      int n = service.Regi_agree(paraMap);
      JSONObject jsonObj = new JSONObject();
      
      if(n== 1) {
         jsonObj.put("n", n);
      }
      
      return jsonObj.toString(); // "{"n":1,"name":"������"}" �Ǵ� "{"n":0,"name":"������"}"
      
   } //end of ���� ����
   
   
   
}