// import 'package:bloodify_front_end/modules/home_UI/user_home/userHome.dart';
import 'package:bloodify_front_end/modules/home_UI/institution_home/instHome.dart';
import 'package:bloodify_front_end/modules/institution/eventInstitution.dart';
import 'package:bloodify_front_end/shared/Constatnt/colors.dart';
import 'package:bloodify_front_end/shared/network/local/cach_helper.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:persistent_bottom_nav_bar/persistent_tab_view.dart';
import 'package:restart_app/restart_app.dart';

import '../settings/institution_settings/institution_settings.dart';
import '../transactions_modules/institution_tranaction/InstituteTransaction.dart';
import 'package:bloodify_front_end/modules/institution/stats/statistics_view.dart';

class InstNavBar extends StatelessWidget {
  const InstNavBar({Key? key}) : super(key: key);

  List<Widget> _buildScreens() {
    return [
      InstitutionHome(),
      const Statistics(),
      InstituteTransaction(),
      InstitutionEventPage(),
      const InstitutionSettings()
    ];
  }

  List<PersistentBottomNavBarItem> _navBarsItems() {
    return [
      PersistentBottomNavBarItem(
        icon: const Icon(CupertinoIcons.house_fill),
        title: ("Home"),
        activeColorPrimary: blue,
        inactiveColorPrimary: grey,
      ),
      PersistentBottomNavBarItem(
        icon: const Icon(Icons.bar_chart),
        title: ("Stats"),
        activeColorPrimary: blue,
        inactiveColorPrimary: grey,
      ),
      PersistentBottomNavBarItem(
        icon: const Icon(
          Icons.done,
          color: Colors.white,
        ),
        title: ("Confirm"),
        activeColorPrimary: red,
        inactiveColorPrimary: grey,
      ),
      PersistentBottomNavBarItem(
        icon: const Icon(Icons.event),
        title: ("Events"),
        activeColorPrimary: blue,
        inactiveColorPrimary: grey,
      ),
      PersistentBottomNavBarItem(
        icon: const Icon(Icons.settings),
        title: ("Settings"),
        activeColorPrimary: blue,
        inactiveColorPrimary: grey,
      ),
    ];
  }

  @override
  Widget build(BuildContext context) {
    PersistentTabController _controller;
    _controller = PersistentTabController(initialIndex: 0);
    return PersistentTabView(
      context,
      controller: _controller,
      screens: _buildScreens(),
      items: _navBarsItems(),
      confineInSafeArea: true,
      backgroundColor: Colors.white, // Default is Colors.white.
      handleAndroidBackButtonPress: true, // Default is true.
      resizeToAvoidBottomInset:
          true, // This needs to be true if you want to move up the screen when keyboard appears. Default is true.
      stateManagement: true, // Default is true.
      hideNavigationBarWhenKeyboardShows:
          true, // Recommended to set 'resizeToAvoidBottomInset' as true while using this argument. Default is true.
      decoration: NavBarDecoration(
        borderRadius: BorderRadius.circular(10.0),
        colorBehindNavBar: Colors.white,
      ),
      popAllScreensOnTapOfSelectedTab: true,
      popActionScreens: PopActionScreensType.all,
      itemAnimationProperties: const ItemAnimationProperties(
        // Navigation Bar's items animation properties.
        duration: Duration(milliseconds: 200),
        curve: Curves.ease,
      ),
      screenTransitionAnimation: const ScreenTransitionAnimation(
        // Screen transition animation on change of selected tab.
        animateTabTransition: true,
        curve: Curves.ease,
        duration: Duration(milliseconds: 200),
      ),
      navBarStyle:
          NavBarStyle.style15, // Choose the nav bar style with this property.
    );
  }
}

class Screen2 extends StatelessWidget {
  const Screen2({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Screen2')),
      body: const Center(
        child: Text(
          'Notifications',
          style: TextStyle(fontSize: 30),
        ),
      ),
    );
  }
}

class Screen3 extends StatelessWidget {
  const Screen3({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Screen3')),
      body: const Center(
        child: Text(
          'Request',
          style: TextStyle(fontSize: 30),
        ),
      ),
    );
  }
}

class Screen4 extends StatelessWidget {
  const Screen4({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Screen4')),
      body: const Center(
        child: Text(
          'Events',
          style: TextStyle(fontSize: 30),
        ),
      ),
    );
  }
}

class Screen5 extends StatelessWidget {
  const Screen5({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Screen5')),
      floatingActionButton: FloatingActionButton(
        onPressed: logout,
        child: const Icon(Icons.logout),
      ),
      body: const Center(
        child: Text(
          'Account',
          style: TextStyle(fontSize: 30),
        ),
      ),
    );
  }

  void logout() {
    CachHelper.removeData(key: "token");
    CachHelper.removeData(key: "isUser");
    // Restart.restartApp(webOrigin: '/');
  }
}
