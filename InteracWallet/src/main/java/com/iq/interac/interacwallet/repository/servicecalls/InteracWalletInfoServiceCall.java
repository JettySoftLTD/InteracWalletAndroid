package com.iq.interac.interacwallet.repository.servicecalls;

import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.InteracAPI;
import com.iq.interac.interacwallet.interfaces.InteracServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.RetrofitService;
import com.iq.interac.interacwallet.repository.PathURL;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

public class InteracWalletInfoServiceCall {
    InteracServiceCallHelper listener;

    public InteracWalletInfoServiceCall(InteracServiceCallHelper listener) {
        this.listener = listener;
    }

    public void getWalletInfo(String interacToken, int merchantId, int customerId, String branchId) {
        try {
            String url =
                    PathURL.getWalletInfo + "token=" + interacToken + "&merchantId=" + merchantId + "&customerId=" + customerId + "&branchId=" + branchId;

            RetrofitService.getInstance().postDataInteracObject(url, new InteracAPI() {
                @Override
                public void notifySuccess(int status, String message, InteracMainBean imb) {
                    if (status == 200) {
                        listener.notifyInteracServiceResponse(true, message, imb);
                    } else {
                        listener.notifyInteracServiceResponse(false, message, null);
                    }
                }

                @Override
                public void notifyError(String errormessage) {
                    listener.notifyInteracServiceResponse(false, errormessage, null);
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public void getAmountLoaded(String interacToken, int walletId, int merchantId, int customerId) {
        try {
            String url = PathURL.getWalletTransactions + "token=" + interacToken + "&merchantId=" + merchantId + "&customerId=" + customerId + "&walletId=" + walletId;

            RetrofitService.getInstance().postDataInteracObject(url, new InteracAPI() {
                @Override
                public void notifySuccess(int status, String message, InteracMainBean imb) {
                    if (status == 200) {
                        listener.notifyInteracServiceResponse(true, message, imb);
                    } else {
                        listener.notifyInteracServiceResponse(false, message, null);
                    }
                }

                @Override
                public void notifyError(String errormessage) {
                    listener.notifyInteracServiceResponse(false, errormessage, null);
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                // FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public void getAmountUsed(String interacToken, int walletId, int merchantId, int customerId) {
        try {
            String url = PathURL.getPaymentTransactions + "token=" + interacToken + "&merchantId=" + merchantId + "&customerId=" + customerId + "&walletId=" + walletId;

            RetrofitService.getInstance().postDataInteracObject(url, new InteracAPI() {
                @Override
                public void notifySuccess(int status, String message, InteracMainBean imb) {
                    if (status == 200) {
                        listener.notifyInteracServiceResponse(true, message, imb);
                    } else {
                        listener.notifyInteracServiceResponse(false, message, null);
                    }
                }

                @Override
                public void notifyError(String errormessage) {
                    listener.notifyInteracServiceResponse(false, errormessage, null);
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public void getAmountReceived(String interacToken, int walletId, int merchantId, int customerId) {
        try {
            String url = PathURL.getAmountReceivedTransactions + "token=" + interacToken + "&merchantId=" + merchantId + "&customerId=" + customerId + "&walletId=" + walletId;

            RetrofitService.getInstance().postDataInteracObject(url, new InteracAPI() {
                @Override
                public void notifySuccess(int status, String message, InteracMainBean imb) {
                    if (status == 200) {
                        listener.notifyInteracServiceResponse(true, message, imb);
                    } else {
                        listener.notifyInteracServiceResponse(false, message, null);
                    }
                }

                @Override
                public void notifyError(String errormessage) {
                    listener.notifyInteracServiceResponse(false, errormessage, null);
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                // FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public void getAmountSent(String interacToken, int walletId, int merchantId, int customerId) {
        try {
            String url = PathURL.getAmountSentTransactions + "token=" + interacToken + "&merchantId=" + merchantId + "&customerId=" + customerId + "&walletId=" + walletId;

            RetrofitService.getInstance().postDataInteracObject(url, new InteracAPI() {
                @Override
                public void notifySuccess(int status, String message, InteracMainBean imb) {
                    if (status == 200) {
                        listener.notifyInteracServiceResponse(true, message, imb);
                    } else {
                        listener.notifyInteracServiceResponse(false, message, null);
                    }
                }

                @Override
                public void notifyError(String errormessage) {
                    listener.notifyInteracServiceResponse(false, errormessage, null);
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                // FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public void transferFunds(String interacToken, int walletId, String receivingPhoneNumber, double amount, int merchantId, int customerId, String sendingPhoneNumber, String branchId) {
        try {
            String url = PathURL.transferFunds + "token=" + interacToken + "&merchantId=" + merchantId + "&customerId=" + customerId
                    + "&sendingWalletId=" + walletId + "&sendingPhoneNumber=" + sendingPhoneNumber + "&receivingPhoneNumber=" + receivingPhoneNumber + "&sendingBranchId=" + branchId
                    + "&amount=" + amount;

            RetrofitService.getInstance().postDataInteracObject(url, new InteracAPI() {
                @Override
                public void notifySuccess(int status, String message, InteracMainBean imb) {
                    if (status == 200) {
                        listener.notifyInteracServiceResponse(true, message, imb);
                    } else {
                        listener.notifyInteracServiceResponse(false, message, null);
                    }
                }

                @Override
                public void notifyError(String errormessage) {
                    listener.notifyInteracServiceResponse(false, errormessage, null);
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }
}
