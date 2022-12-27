import 'package:flutter/material.dart';
import 'dart:ui';
import 'package:google_fonts/google_fonts.dart';

TextStyle SafeGoogleFont(
  String fontFamily, {
  TextStyle? textStyle,
  Color? color,
  Color? backgroundColor,
  double? fontSize,
  FontWeight? fontWeight,
  FontStyle? fontStyle,
  double? letterSpacing,
  double? wordSpacing,
  TextBaseline? textBaseline,
  double? height,
  Locale? locale,
  Paint? foreground,
  Paint? background,
  List<Shadow>? shadows,
  List<FontFeature>? fontFeatures,
  TextDecoration? decoration,
  Color? decorationColor,
  TextDecorationStyle? decorationStyle,
  double? decorationThickness,
}) {
  try {
    return GoogleFonts.getFont(
      fontFamily,
      textStyle: textStyle,
      color: color,
      backgroundColor: backgroundColor,
      fontSize: fontSize,
      fontWeight: fontWeight,
      fontStyle: fontStyle,
      letterSpacing: letterSpacing,
      wordSpacing: wordSpacing,
      textBaseline: textBaseline,
      height: height,
      locale: locale,
      foreground: foreground,
      background: background,
      shadows: shadows,
      fontFeatures: fontFeatures,
      decoration: decoration,
      decorationColor: decorationColor,
      decorationStyle: decorationStyle,
      decorationThickness: decorationThickness,
    );
  } catch (ex) {
    return GoogleFonts.getFont(
      "Source Sans Pro",
      textStyle: textStyle,
      color: color,
      backgroundColor: backgroundColor,
      fontSize: fontSize,
      fontWeight: fontWeight,
      fontStyle: fontStyle,
      letterSpacing: letterSpacing,
      wordSpacing: wordSpacing,
      textBaseline: textBaseline,
      height: height,
      locale: locale,
      foreground: foreground,
      background: background,
      shadows: shadows,
      fontFeatures: fontFeatures,
      decoration: decoration,
      decorationColor: decorationColor,
      decorationStyle: decorationStyle,
      decorationThickness: decorationThickness,
    );
  }
}

TextStyle HeadingStyle(double height, Color color) {
  return SafeGoogleFont('Poppins',
      fontSize: 0.025 * height,
      fontWeight: FontWeight.w500,
      height: 1.5,
      color: color);
}

TextStyle AppHeaderStyle(double height, Color color) {
  return SafeGoogleFont('Poppins',
      fontSize: 0.034 * height,
      fontWeight: FontWeight.w500,
      height: 1.5,
      color: color);
}

TextStyle SmallStyle(double width, Color color) {
  return SafeGoogleFont(
    'Poppins',
    fontSize: 0.0359 * width,
    fontWeight: FontWeight.w400,
    color: color,
  );
}

TextStyle SmallBoldStyle(double width, Color color) {
  return SafeGoogleFont(
    'Poppins',
    fontSize: 0.0359 * width,
    fontWeight: FontWeight.w700,
    color: color,
  );
}

TextStyle VerySmallStyle(double width, Color color) {
  return SafeGoogleFont(
    'Poppins',
    fontSize: 0.031 * width,
    fontWeight: FontWeight.w400,
    color: color,
  );
}

TextStyle VerySmallBoldStyle(double width, Color color) {
  return SafeGoogleFont(
    'Poppins',
    fontSize: 0.031 * width,
    fontWeight: FontWeight.w700,
    color: color,
  );
}

TextStyle NormalStyle(double height, Color color) {
  return SafeGoogleFont(
    'Poppins',
    fontSize: 0.025 * height,
    fontWeight: FontWeight.w700,
    color: color,
  );
}

TextStyle BigStyle(double height, Color color) {
  return SafeGoogleFont(
    'Poppins',
    fontSize: 0.04 * height,
    fontWeight: FontWeight.w700,
    color: color,
  );
}
