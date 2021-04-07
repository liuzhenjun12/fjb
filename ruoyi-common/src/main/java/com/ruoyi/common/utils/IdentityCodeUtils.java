package com.ruoyi.common.utils;

public class IdentityCodeUtils {
    /**
     * 判断身份证格式
     *
     * @return
     */
        final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
        final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
        private int[] ai = new int[18];
        public boolean Verify(String idcard) {
            if (idcard.length() == 15) {
                idcard = uptoeighteen(idcard);
            }
            if (idcard.length() != 18) {
                return false;
            }
            String verify = idcard.substring(17, 18);
            if (verify.equals(getVerify(idcard))) {
                return true;
            }
            return false;
        }

        public String getVerify(String eightcardid) {
            int remaining = 0;

            if (eightcardid.length() == 18) {
                eightcardid = eightcardid.substring(0, 17);
            }

            if (eightcardid.length() == 17) {
                int sum = 0;
                for (int i = 0; i < 17; i++) {
                    String k = eightcardid.substring(i, i + 1);
                    ai[i] = Integer.parseInt(k);
                }

                for (int i = 0; i < 17; i++) {
                    sum = sum + wi[i] * ai[i];
                }
                remaining = sum % 11;
            }

            return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
        }

        public String uptoeighteen(String fifteencardid) {
            String eightcardid = fifteencardid.substring(0, 6);
            eightcardid = eightcardid + "19";
            eightcardid = eightcardid + fifteencardid.substring(6, 15);
            eightcardid = eightcardid + getVerify(eightcardid);
            return eightcardid;
        }
    public static void main(String[] args) {
        String idcard1 = "440923199304101799";
        String idcard2 = "440981198602125624";
        String idcard3 = "440981198602125625";

        IdentityCodeUtils idcard = new IdentityCodeUtils();
        System.out.println(idcard.Verify(idcard1));
        System.out.println(idcard.Verify(idcard2));
        System.out.println(idcard.Verify(idcard3));
    }
}
