package com.yeluo.mvn.ch2;

import java.util.Arrays;
import java.util.Locale;

/**
 * 
 * @author YeLuo
 * @function 测试Locale功能
 */
/**
 * 1.Locale 对象表示了特定的地理、政治和文化地区。
 * 需要 Locale 来执行其任务的操作称为语言环境敏感的 操作，它使用 Locale 为用户量身定制信息。
 * 例如，显示一个数值就是语言环境敏感的操作，应该根据用户的国家、地区或文化的风俗/传统来格式化该数值。 
 */
		
public class LocaleTest {
	public static void main(String[] args) {
		
		methodTest();
		LocaleTest();
		
	}
	/**
	 * static Locale getDefault() 
	 * 获得此 Java 虚拟机实例的当前默认语言环境值。
	 * 
	 *  String getCountry() 
	 *  返回此语言环境的国家/地区代码，将为空字符串或大写的 ISO 3166 两字母代码。
	 *  
	 *  static Locale[] getAvailableLocales() 
	 *  返回所有已安装语言环境的数组 
	 *  
	 *  String getDisplayCountry() 
	 *    返回适合向用户显示的语言环境国家/地区名。
	 *    
	 *  String getDisplayCountry(Locale inLocale)
	 *  返回适合向用户显示的语言环境国家/地区名。 
	 *  
	 *  public final String getDisplayLanguage()
	 *  返回适合向用户显示的语言环境语言名
	 *  
	 *  public final String getDisplayVariant()
	 *  返回适合向用户显示的语言环境变量代码名。
	 *  如果该语言环境未指定变量代码，则此功能返回空字符串。  
	 *  
	 *  public final String getDisplayName()
	 *  返回适合向用户显示的语言环境名。 
	 *  
	 *  String getISO3Country()  
	 *  返回此语言环境国家/地区的三字母缩写。
	 *   
	 *  String getDisplayName(Locale inLocale) 
	 *  返回此语言环境语言的三字母缩写。
	 *  
	 *  static String[] getISOCountries()  
	 *  返回 ISO 3166 中所定义的所有两字母国家/地区代码。
	 */
	private static void methodTest() {
		//获得此 Java 虚拟机实例的当前默认语言环境值
		Locale defaultLocale=Locale.getDefault();
		System.out.println(defaultLocale);   //zh_CN
		
		//返回此语言环境的国家/地区代码
		String country=defaultLocale.getCountry();
		System.out.println(country); 		//CN
		
		//返回所有已安装语言环境的数组
		Locale[] locales=Locale.getAvailableLocales();
		System.out.println(Arrays.toString(locales));
		//output:[, ar_AE, ar_JO, ar_SY, hr_HR, fr_BE, es_PA, mt_MT, es_VE, bg, zh_TW, it, ko, uk, lv, da_DK, es_PR, vi_VN, en_US, sr_ME, sv_SE, es_BO, en_SG, ar_BH, pt, ar_SA, sk, ar_YE, hi_IN, ga, en_MT, fi_FI, et, sv, cs, sr_BA_#Latn, el, uk_UA, hu, fr_CH, in, es_AR, ar_EG, ja_JP_JP_#u-ca-japanese, es_SV, pt_BR, be, is_IS, cs_CZ, es, pl_PL, tr, ca_ES, sr_CS, ms_MY, hr, lt, es_ES, es_CO, bg_BG, sq, fr, ja, sr_BA, is, es_PY, de, es_EC, es_US, ar_SD, en, ro_RO, en_PH, ca, ar_TN, sr_ME_#Latn, es_GT, sl, ko_KR, el_CY, es_MX, ru_RU, es_HN, zh_HK, no_NO_NY, hu_HU, th_TH, ar_IQ, es_CL, fi, ar_MA, ga_IE, mk, tr_TR, et_EE, ar_QA, sr__#Latn, pt_PT, fr_LU, ar_OM, th, sq_AL, es_DO, es_CU, ar, ru, en_NZ, sr_RS, de_CH, es_UY, ms, el_GR, iw_IL, en_ZA, th_TH_TH_#u-nu-thai, hi, fr_FR, de_AT, nl, no_NO, en_AU, vi, nl_NL, fr_CA, lv_LV, de_LU, es_CR, ar_KW, sr, ar_LY, mt, it_CH, da, de_DE, ar_DZ, sk_SK, lt_LT, it_IT, en_IE, zh_SG, ro, en_CA, nl_BE, no, pl, zh_CN, ja_JP, de_GR, sr_RS_#Latn, iw, en_IN, ar_LB, es_NI, zh, mk_MK, be_BY, sl_SI, es_PE, in_ID, en_GB]

		//返回适合向用户显示的语言环境国家/地区名。
		String display=defaultLocale.getDisplayCountry();
		System.out.println(display);  //中国
		
		System.out.println(Locale.UK.getDisplayCountry(Locale.FRENCH));  //Royaume-Uni
		
		//返回适合向用户显示的语言环境语言名
		String displayLan=defaultLocale.getLanguage();
		System.out.println(displayLan);   //zh
		
		// 返回适合向用户显示的语言环境变量代码名。
		String displayVa=defaultLocale.getVariant();
		System.out.println(displayVa);
		
		//返回适合向用户显示的语言环境名。
		String name=defaultLocale.getDisplayName();
		System.out.println(name);  //中文 (中国)
		
		//返回此语言环境国家/地区的三字母缩写。
		String country3=defaultLocale.getISO3Country();
		System.out.println(country3);   //CHN
		
		// 返回此语言环境语言的三字母缩写。
		String language3=defaultLocale.getISO3Language();
		System.out.println(language3);  //zho
		
		//返回 ISO 3166 中所定义的所有两字母国家/地区代码。
		String[] countrys=Locale.getISOCountries();
		System.out.println(Arrays.toString(countrys));
		//output:[AD, AE, AF, AG, AI, AL, AM, AN, AO, AQ, AR, AS, AT, AU, AW, AX, AZ, BA, BB, BD, BE, BF, BG, BH, BI, BJ, BL, BM, BN, BO, BQ, BR, BS, BT, BV, BW, BY, BZ, CA, CC, CD, CF, CG, CH, CI, CK, CL, CM, CN, CO, CR, CU, CV, CW, CX, CY, CZ, DE, DJ, DK, DM, DO, DZ, EC, EE, EG, EH, ER, ES, ET, FI, FJ, FK, FM, FO, FR, GA, GB, GD, GE, GF, GG, GH, GI, GL, GM, GN, GP, GQ, GR, GS, GT, GU, GW, GY, HK, HM, HN, HR, HT, HU, ID, IE, IL, IM, IN, IO, IQ, IR, IS, IT, JE, JM, JO, JP, KE, KG, KH, KI, KM, KN, KP, KR, KW, KY, KZ, LA, LB, LC, LI, LK, LR, LS, LT, LU, LV, LY, MA, MC, MD, ME, MF, MG, MH, MK, ML, MM, MN, MO, MP, MQ, MR, MS, MT, MU, MV, MW, MX, MY, MZ, NA, NC, NE, NF, NG, NI, NL, NO, NP, NR, NU, NZ, OM, PA, PE, PF, PG, PH, PK, PL, PM, PN, PR, PS, PT, PW, PY, QA, RE, RO, RS, RU, RW, SA, SB, SC, SD, SE, SG, SH, SI, SJ, SK, SL, SM, SN, SO, SR, SS, ST, SV, SX, SY, SZ, TC, TD, TF, TG, TH, TJ, TK, TL, TM, TN, TO, TR, TT, TV, TW, TZ, UA, UG, UM, US, UY, UZ, VA, VC, VE, VG, VI, VN, VU, WF, WS, YE, YT, ZA, ZM, ZW]

	}

	/**
	 * 1.构造方法
	 *  Locale(String language) 
                       根据语言代码构造一个语言环境。 
		Locale(String language, String country) 
                       根据语言和国家/地区构造一个语言环境。 
		Locale(String language, String country, String variant) 
                        根据语言、国家/地区和变量构造一个语言环境。 
		语言参数是一个有效的 ISO 语言代码。这些代码是由 ISO-639 定义的小写两字母代码。
		在许多网站上都可以找到这些代码的完整列表，如：
		http://www.loc.gov/standards/iso639-2/englangn.html。 
		国家/地区参数是一个有效的 ISO 国家/地区代码。这些代码是由 ISO-3166 定义的大写两字母代码。
		在许多网站上都可以找到这些代码的完整列表，如：
		http://www.iso.ch/iso/en/prods-services/iso3166ma/02iso-3166-code-lists/list-en1.html。 

		2.static void setDefault(Locale newLocale) 
		为此 Java 虚拟机实例设置默认语言环境。
	 */
	private static void LocaleTest() {
		String language="zh";
		String country="CN";
		String variant="";
		Locale defaultLocale=Locale.US;
		
		System.out.println(defaultLocale.getCountry());
		
		String display=defaultLocale.getDisplayCountry();
		System.out.println(display);  //美国
		
		Locale.setDefault(Locale.UK);
		System.out.println(Locale.getDefault());  //en_GB
	}
}
